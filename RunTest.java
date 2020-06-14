package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/java/features/bigbasket.feature", glue ="steps")

public class RunTest extends AbstractTestNGCucumberTests{

}
