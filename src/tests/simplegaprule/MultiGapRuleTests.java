package simplegaprule;

import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class MultiGapRuleTests {
	@Test
	public void testAllTestCasesCount() {
		Optional<List<SimpleGapRule>> result = Stream.of("test-case.json", "test-folder")
			.map(SimpleGapRule::loadFile)
			.reduce((a, b) -> Stream.of(a, b).flatMap(List::stream).collect(Collectors.toList()));
		
		assertTrue(result.isPresent());
		assertEquals(4, result.get().size());
	}
	
	@Test
	public void testFolderTestCasesCount() {
		Optional<List<SimpleGapRule>> result = Stream.of("test-folder")
			.map(SimpleGapRule::loadFile)
			.reduce((a, b) -> Stream.of(a, b).flatMap(List::stream).collect(Collectors.toList()));
		
		assertTrue(result.isPresent());
		assertEquals(3, result.get().size());
	}
	
	@Test
	public void testSingleFileTestCasesCount() {
		Optional<List<SimpleGapRule>> result = Stream.of("test-case.json")
			.map(SimpleGapRule::loadFile)
			.reduce((a, b) -> Stream.of(a, b).flatMap(List::stream).collect(Collectors.toList()));
		
		assertTrue(result.isPresent());
		assertEquals(1, result.get().size());
	}
}