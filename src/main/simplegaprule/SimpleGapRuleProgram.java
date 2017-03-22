package simplegaprule;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import simplegaprule.models.Campsite;
import simplegaprule.models.CampspotEnvironment;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Simple gap rule implementation.
 * 
 * Gap rule:
 * Most destination resorts don’t host guests who are willing to stay for only one night.
 * Their guests are not simply passing through the area; they’ve often travelled far with
 * lots of luggage specifically to stay there, and it’s not worth it for them to stay one
 * night. As a result, a reservation system for resorts needs to prevent one night gaps in
 * its reservation grid, or schedule of inventory. If one-night gaps occur on the grid,
 * they will not be sold, and this results in lost income for the resort owner. For example,
 * Campspot uses a one-night gap rule, as this is a common problem for camping resort
 * owners. However, owners’ gap problems aren’t limited to one-night gaps. Some resorts
 * only host guests who stay a minimum three or four nights. Those resorts may want to
 * prevent one-, two-, and three-night gaps.
 * 
 * @author Braden Steffaniak
 */
public class SimpleGapRuleProgram {
	private CampspotEnvironment environment;
	
	public static final DateTimeFormatter DEFAULT_DATE_FORMAT = DateTimeFormat.forPattern("yyyy-MM-dd");
	
	/**
	 * Command line gap rule runner. Takes files as input and outputs the results of the
	 * gap rule to the standard output.
	 * 
	 * @param args The input json file locations to perform the gap rule test on.
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			throw new IllegalArgumentException("Expected input files as arguments; e.g. '" +
				SimpleGapRuleProgram.class.getSimpleName() + " filename1.json directoryName filenameN.json'");
		}
		
		Arrays.stream(args)
			.map(SimpleGapRuleProgram::loadFile)
			.forEach(rules -> rules.forEach(rule -> {
				rule.getAvailableCampsites().forEach(System.out::println);
			}));
	}
	
	/**
	 * Load a file from a given fileLocation String.
	 * 
	 * @param fileLocation The location of the file relative to the application's working directory.
	 * @return The list of runnable test cases.
	 */
	public static List<SimpleGapRuleProgram> loadFile(String fileLocation) {
		return loadFile(new File(fileLocation));
	}
	
	/**
	 * Load test cases from the given file. If the file is a directory, will search
	 * recursively for json files.
	 * 
	 * @param file The file or directory to load the test case(s) from.
	 * @return The list of runnable test cases.
	 */
	public static List<SimpleGapRuleProgram> loadFile(File file) {
		return loadFile(file, false);
	}
	
	/**
	 * Load test cases from the given file. If the file is a directory, will search
	 * recursively for json files.
	 *
	 * @param file The file or directory to load the test case(s) from.
	 * @param subdirectory Whether or not is currently searching in a directory   
	 * @return The list of runnable test cases.
	 */
	private static List<SimpleGapRuleProgram> loadFile(File file, boolean subdirectory) {
		if (!file.exists()) {
			throw new IllegalArgumentException("Input file '" + file.getPath() + "' does not exist");
		} else if (file.isDirectory()) {
			// Reduce recursively returned lists into a single list, or return empty list
			return Arrays.stream(file.listFiles()).map(x -> loadFile(x, true))
				.reduce((a, b) -> Stream.of(a, b).flatMap(List::stream).collect(Collectors.toList()))
				.orElse(Collections.emptyList());
		} else if (!file.getName().toLowerCase().endsWith(".json")) {
			if (!subdirectory) { // only throw invalid input file if the file was given explicitly
				throw new IllegalArgumentException("Invalid input file '" + file.getPath() + "'");
			} else {
				return Collections.emptyList();
			}
		} else {
			// Return list with single element
			return Collections.singletonList(new SimpleGapRuleProgram(file));
		}
	}
	
	/**
	 * Initialize the SimpleGapRule instance fields. Prepare for running test case.
	 * 
	 * @param jsonFile The file to load the test data from.
	 */
	public SimpleGapRuleProgram(File jsonFile) {
		ObjectMapper mapper = new ObjectMapper();
		
		// Add support for Joda DateTime encoding/decoding from JSON
		mapper.registerModule(new JodaModule());
		
		try {
			environment = mapper.readValue(jsonFile, CampspotEnvironment.class);
		} catch (IOException e) {
			throw new RuntimeException("Unable to load environment from json file.", e);
		}
	}
	
	/**
	 * Get the generated {@link CampspotEnvironment Campspot test case environment model}.
	 * 
	 * @return The generated {@link CampspotEnvironment} instance.
	 */
	public CampspotEnvironment getEnvironment() {
		return environment;
	}
	
	/**
	 * Get the available Campsites with the specified Search date criteria given
	 * in the test case json file.
	 * 
	 * @return A List of Campsites.
	 */
	public List<Campsite> getAvailableCampsites() {
		return Arrays.stream(environment.getCampsites())
			.filter(x -> x.isReservationTimeAvailable(environment, environment.getSearch()))
			.collect(Collectors.toList());
	}
}