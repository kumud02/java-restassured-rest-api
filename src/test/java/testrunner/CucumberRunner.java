package testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = {"pretty","html:target/html-report","json:target/cucumber.json"},
        features = { "src/test/java/features/"},
        glue = {"stepDefinition"},
        monochrome = true
)
public class CucumberRunner extends AbstractTestNGCucumberTests {
}

