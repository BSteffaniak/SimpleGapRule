package simplegaprule;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	ModelTests.class,
	GapRuleTests.class,
	FileInputTests.class
})
public class TestRunner {
	
}