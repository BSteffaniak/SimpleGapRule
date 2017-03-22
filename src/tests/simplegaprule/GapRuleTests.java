package simplegaprule;

import org.junit.Before;
import org.junit.Test;
import simplegaprule.models.Campsite;
import simplegaprule.models.GapRule;
import simplegaprule.models.Reservation;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class GapRuleTests
{
	@Test
	public void testGivenTestCase() {
		SimpleGapRuleProgram rule = new SimpleGapRuleProgram(new File("test-case.json"));
		
		rule.getAvailableCampsites();
	}
}