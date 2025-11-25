package step_definitions.hooks;

import browser.BrowserManager;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;

public class Hook {
    private final BrowserManager browserManager;

    public Hook(BrowserManager browserManager) {
        this.browserManager = browserManager;
    }

    // Runs before all test starts
    @BeforeAll
    public static void beforeAll() {
    }

    // Runs after all test done
    @AfterAll
    public static void afterAll() {
    }

    // Runs before each test
    @Before
    public void setup() {
        browserManager.setUp();
    }

    // Runs after each test
    @After
    public void tearDown() {
        browserManager.tearDown();
    }
}
