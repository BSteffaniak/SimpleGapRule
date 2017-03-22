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
	
	@Test
	public void testReservationContent() {
		int[] ids = new int[] { 1, 1, 2, 3, 3, 4, 4, 5, 5, 6, 6, 6, 7, 7, 7, 8, 8, 9, 9 };
		String[] dateRanges = new String[] {
			"2016-06-01 to 2016-06-04",
			"2016-06-11 to 2016-06-13",
			"2016-06-08 to 2016-06-09",
			"2016-06-04 to 2016-06-06",
			"2016-06-14 to 2016-06-16",
			"2016-06-03 to 2016-06-05",
			"2016-06-13 to 2016-06-14",
			"2016-06-03 to 2016-06-06",
			"2016-06-12 to 2016-06-14",
			"2016-06-04 to 2016-06-06",
			"2016-06-11 to 2016-06-12",
			"2016-06-16 to 2016-06-16",
			"2016-06-03 to 2016-06-04",
			"2016-06-07 to 2016-06-09",
			"2016-06-13 to 2016-06-16",
			"2016-06-01 to 2016-06-02",
			"2016-06-05 to 2016-06-06",
			"2016-06-03 to 2016-06-05",
			"2016-06-12 to 2016-06-16"
		};
		
		for (int i = 0; i < ids.length; i++)
		{
			Reservation reservation = rule.getEnvironment().getReservations()[i];
			
			assertEquals(ids[i], reservation.getCampsiteId());
			assertEquals(dateRanges[i], reservation.getStartDate().toString(SimpleGapRule.DEFAULT_DATE_FORMAT) + " to " + reservation.getEndDate().toString(SimpleGapRule.DEFAULT_DATE_FORMAT));
		}
	}
}