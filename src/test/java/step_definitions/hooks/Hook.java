package step_definitions.hooks;

import browser.BrowserManager;
import io.cucumber.java.*;

public class Hook {
    private final BrowserManager browserManager;

    public Hook(BrowserManager browserManager) {
        this.browserManager = browserManager;
    }

    @BeforeAll
    public static void beforeAll() {
    }

    @AfterAll
    public static void afterAll() {
    }

    @Before
    public void setup() {
        browserManager.setUp();
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = browserManager.takeScreenshot();
            scenario.attach(screenshot, "image/png", "screenshot");
        }
        browserManager.tearDown();
    }
}
