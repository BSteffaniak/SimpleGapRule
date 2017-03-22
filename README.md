# SimpleGapRule
Simple campsite gap rule implementation

__The algorithm__

Given a search time interval, to validate that the search interval does not violate the gap rule, the program will do the following for each campsite:

 1. Get the campsite's reservation times that are adjacent to the given search time interval.
    1. An adjacent interval is one that does not overlap the search time interval
    2. An adjacent interval has the least absolute distance from the search interval compared to all other intervals on the side that the current interval of interest is located.
 2. Validate that the distance between each adjacent reservation interval and the search interval is not equal to a specified gap rule.

If both points are valid, then the search time is available for the tested campsite.

Assumptions:
 * "Teddy *Rosevelt* Tent Site" was a typo in the test-case.json (I did not fix) 

__Running the project from the pre-packaged Jar__

 1. Open command line and navigate to the root project directory
 2. Run `java -jar SimpleGapRule.jar test-case.json`

__Building and running the project from IntelliJ IDEA__

 1. Open IntelliJ IDEA and select File->Open...
 2. Select the cloned git repo from the directory browser
 
 * Select the "Executable" run configuration to simulate passing in the default test-case.json file
 * Select the "Tests" run configuration to perform the unit tests on the program.

__Building the project from Apache Ant__

 1. Open command line and navigate to root project directory
 2. Run the `ant` command

__Running the jar program from command line__

 * Windows: `java -cp out/production/SimpleGapRule;"lib/*" simplegaprule.SimpleGapRuleProgram test-case.json`
 * Non-Windows: `java -cp out/production/SimpleGapRule:lib/* simplegaprule.SimpleGapRuleProgram test-case.json`

__Running JUnit tests from command line:__

Navigate to root project directory in command line and run the following command:

 * Windows: `java -cp out/test/SimpleGapRule;out/production/SimpleGapRule;"lib/*" org.junit.runner.JUnitCore simplegaprule.TestRunner`
 * Non-Windows: `java -cp out/test/SimpleGapRule:out/production/SimpleGapRule:lib/* org.junit.runner.JUnitCore simplegaprule.TestRunner`

The output should resemble:

    JUnit version 4.12
    ................
    Time: 0.559
    
    OK (16 tests)