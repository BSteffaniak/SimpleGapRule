package simplegaprule;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class SimpleGapRuleTests {
	private SimpleGapRule rule;
	
	@Before
	public void setup() {
		rule = new SimpleGapRule(new File("test-case.json"));
	}
	
	@Test
	public void testSearchDate() {
		assertEquals("2016-06-07", rule.getEnvironment().getSearch().getFormattedStartDate());
		assertEquals("2016-06-10", rule.getEnvironment().getSearch().getFormattedEndDate());
	}
}