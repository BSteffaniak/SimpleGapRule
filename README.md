# SimpleGapRule
Simple campsite gap rule implementation

__Running the jar program from command line__

 * Windows: `java -cp out/production/SimpleGapRule;"lib/*" simplegaprule.SimpleGapRule test-case.json`
 * Non-Windows: `java -cp out/production/SimpleGapRule:lib/* simplegaprule.SimpleGapRule test-case.json`

__Running JUnit tests from command line:__

Navigate to root project directory in command line and run the following command:

 * Windows: `java -cp out/test/SimpleGapRule;out/production/SimpleGapRule;"lib/*" org.junit.runner.JUnitCore simplegaprule.TestRunner`
 * Non-Windows: `java -cp out/test/SimpleGapRule:out/production/SimpleGapRule:lib/* org.junit.runner.JUnitCore simplegaprule.TestRunner`
 
The output should resemble:

    JUnit version 4.12
    .....
    Time: 0.547
    
    OK (5 tests)

