package step_definitions;

import browser.BrowserManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.awt.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ContactUs_Steps {
    public BrowserManager browserManager;

    public ContactUs_Steps(BrowserManager browserManager) {
        this.browserManager = browserManager;
    }

    @When("I type a first name")
    public void i_type_a_first_name() {
        browserManager.page.getByPlaceholder("First Name").fill("Joe");
    }

    @When("I type a last name")
    public void i_type_a_last_name() {
        browserManager.page.getByPlaceholder("Last Name").fill("Doe");
    }

    @When("I type a email address")
    public void i_type_a_email_address() {
        browserManager.page.getByPlaceholder("Email Address").fill("Joe@gmail.com");
    }

    @When("I type a comment")
    public void i_type_a_comment() {
        browserManager.page.getByPlaceholder("Comments").fill("Playwright");
    }

    @When("I click on the submit button")
    public void i_click_on_the_submit_button() {
        Page.WaitForSelectorOptions options = new Page.WaitForSelectorOptions().setTimeout(3000);
        browserManager.page.waitForSelector("input[value='SUBMIT']", options);
        browserManager.page.click("input[value='SUBMIT']");
    }

    @Then("I should be presented with a successful contact us submission message")
    public void i_should_be_presented_with_a_successful_contact_us_submission_message() {
        browserManager.page.waitForSelector("h1", new Page.WaitForSelectorOptions().setTimeout(3000));
        Locator locator = browserManager.page.locator("h1");
        assertThat(locator).isVisible();
        assertThat(locator).hasText("Thank You for your Message!");
    }
}
