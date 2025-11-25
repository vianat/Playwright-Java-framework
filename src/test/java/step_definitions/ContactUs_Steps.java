package step_definitions;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.awt.*;

public class ContactUs_Steps {
    private final Page page;
    private final BrowserContext browserContext;

    public ContactUs_Steps() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)screenSize.getWidth();
        int height = (int)screenSize.getHeight();
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(width, height));
        page = browserContext.newPage();
    }

    @When("I type a first name")
    public void i_type_a_first_name() {
        page.getByPlaceholder("First Name").fill("Joe");
        page.pause();
    }
    @When("I type a last name")
    public void i_type_a_last_name() {
    }
    @When("I type a email address")
    public void i_type_a_email_address() {
    }
    @When("I type a comment")
    public void i_type_a_comment() {
    }
    @When("I click on the submit button")
    public void i_click_on_the_submit_button() {
    }
    @Then("I should be presented with a successful contact us submission message")
    public void i_should_be_presented_with_a_successful_contact_us_submission_message() {
    }
}
