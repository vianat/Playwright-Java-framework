package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "step_definitions",
        tags = "@login",
        plugin = {"pretty", "json:target/report.json", "html:target/report.html"}
)
public class RunCucumberTest extends AbstractTestNGCucumberTests {
}
