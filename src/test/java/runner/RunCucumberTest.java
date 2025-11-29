package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.TestNG;
import org.testng.annotations.DataProvider;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "step_definitions",
        tags = "@login",
        plugin = {"pretty", "json:target/report.json", "html:target/report.html"}
)
public class RunCucumberTest extends AbstractTestNGCucumberTests {
    private static final Properties properties = new Properties();
    private static final Logger logger = Logger.getLogger(RunCucumberTest.class.getName());

    static {
        Path configPath = Paths.get(System.getProperty("config.path",
                Paths.get(System.getProperty("user.dir"),"src", "main", "resources", "config.properties").toString()));
        try (InputStream input = Files.newInputStream(configPath)) {
            properties.load(input);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "failed to load prop file");
        }
    }

    public static void main(String[] args) {
        TestNG testNG = new TestNG();
        XmlSuite suite = new XmlSuite();
        suite.setName("Cucumber Tests");

        XmlTest test = new XmlTest(suite);
        test.setName("Cucumber Test");

        // Важно: установить preserveOrder = true и использовать специальное имя data provider
        test.setPreserveOrder(true);

        // Указываем класс, который содержит data provider для Cucumber
        test.setXmlClasses(java.util.Collections.singletonList(new XmlClass(RunCucumberTest.class)));

        testNG.setXmlSuites(java.util.Collections.singletonList(suite));

        // disable default TestNG report
        testNG.setUseDefaultListeners(false);

        testNG.run();
    }

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
