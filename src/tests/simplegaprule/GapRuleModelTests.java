package simplegaprule;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class GapRuleModelTests
{
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
	
	@Test
	public void testCampsiteCount() {
		assertEquals(9, rule.getEnvironment().getCampsites().length);
	}
	
	@Test
	public void testCampsiteContent() {
		String[] values = new String[] {
			"Grizzly Adams Adventure Cabin",
			"Lewis and Clark Camp Spot",
			"Jonny Appleseed Log Cabin",
			"Davey Crockett Camphouse",
			"Daniel Boone Bungalow",
			"Teddy Rosevelt Tent Site",
			"Edmund Hillary Igloo",
			"Bear Grylls Cozy Cave",
			"Wyatt Earp Corral"
		};
		
		for (int i = 0; i < values.length; i++)
		{
			Campsite site = rule.getEnvironment().getCampsites()[i];
			
			assertEquals(i + 1, site.getId());
			assertEquals(values[i], site.getName());
		}
	}
}