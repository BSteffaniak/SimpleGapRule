package simplegaprule;

import org.junit.Test;
import simplegaprule.models.Campsite;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Test the functionality of the gap rule algorithm.
 */
public class GapRuleTests
{
	@Test
	public void testGivenTestCase() {
		SimpleGapRuleProgram rule = new SimpleGapRuleProgram(new File("test-case.json"));
		
		List<Campsite> available = rule.getAvailableCampsites();
		
		assertEquals(4, available.size());
		assertEquals("Daniel Boone Bungalow", available.get(0).getName());
		assertEquals("Teddy Rosevelt Tent Site", available.get(1).getName());
		assertEquals("Bear Grylls Cozy Cave", available.get(2).getName());
		assertEquals("Wyatt Earp Corral", available.get(3).getName());
	}
	
	@Test
	public void testNoOpenings() {
		SimpleGapRuleProgram rule = new SimpleGapRuleProgram(new File("test-folder/no-openings.json"));
		
		List<Campsite> available = rule.getAvailableCampsites();
		
		assertEquals(0, available.size());
	}
	
	@Test
	public void testNoReservations() {
		SimpleGapRuleProgram rule = new SimpleGapRuleProgram(new File("test-folder/no-reservations.json"));
		
		List<Campsite> available = rule.getAvailableCampsites();
		
		assertEquals(rule.getEnvironment().getCampsites().length, available.size());
		assertEquals(0, rule.getEnvironment().getReservations().length);
	}
	
	@Test
	public void testSingleReservationConflict() {
		SimpleGapRuleProgram rule = new SimpleGapRuleProgram(new File("test-folder/one-reservation.json"));
		
		List<Campsite> available = rule.getAvailableCampsites();
		
		assertEquals(rule.getEnvironment().getCampsites().length - 1, available.size());
		assertEquals(1, rule.getEnvironment().getReservations().length);
	}
}