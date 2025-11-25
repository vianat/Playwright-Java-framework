package step_definitions;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import java.awt.*;

public class HomePage_Steps {
    private final Page page;
    private final BrowserContext browserContext;
    private Page mostRecentPage;

    public HomePage_Steps() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)screenSize.getWidth();
        int height = (int)screenSize.getHeight();
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(width, height));
        page = browserContext.newPage();
    }
    @Given("I navigate webdriveruniversity homepage")
    public void i_navigate_webdriveruniversity_homepage() {
        page.navigate("https://www.webdriveruniversity.com/");
    }
    @When("I click on the contact us button")
    public void i_click_on_the_contact_us_button() {
        mostRecentPage = browserContext.waitForPage(() -> {
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("CONTACT US Contact Us Form")).click();
        });
        mostRecentPage.bringToFront();
        mostRecentPage.getByPlaceholder("First Name").fill("Joe");
    }
}
