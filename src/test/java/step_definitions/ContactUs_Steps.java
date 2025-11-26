package step_definitions;

import browser.BrowserManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.datafaker.Faker;

import java.awt.*;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.assertTrue;

public class ContactUs_Steps {
    public BrowserManager browserManager;
    private final Faker faker = new Faker();

    public ContactUs_Steps(BrowserManager browserManager) {
        this.browserManager = browserManager;
    }

    @And("I type a first name {string}")
    public void i_type_a_first_name(String name) {
        browserManager.page.getByPlaceholder("First Name").fill(name);
    }

    @And("I type a last name {string}")
    public void i_type_a_last_name(String name) {
        browserManager.page.getByPlaceholder("Last Name").fill(name);
    }

    @And("I type a email address {string}")
    public void i_type_a_email_address(String email) {
        browserManager.page.getByPlaceholder("Email Address").fill(email);
    }

    @And("I type a comment {string}")
    public void i_type_a_comment(String text) {
        browserManager.page.getByPlaceholder("Comments").fill(text);
    }

    @And("I click on the submit button")
    public void i_click_on_the_submit_button() {
        Page.WaitForSelectorOptions options = new Page.WaitForSelectorOptions().setTimeout(3000);
        browserManager.page.waitForSelector("input[value='SUBMIT']", options);
        browserManager.page.click("input[value='SUBMIT']");
    }

    @Then("I should be presented with a successful contact us submission message {string}")
    public void i_should_be_presented_with_a_successful_contact_us_submission_message(String msg) {
        browserManager.page.waitForSelector("//body");
        List<String> texts = browserManager.page.locator("//body").allInnerTexts();
        String foundText = "";
        boolean flag = false;
        for (String text: texts) {
            if (text.contains(msg)){
                foundText = text;
                flag = true;
                break;
            } else {
                foundText = text;
            }
        }
        assertTrue(flag, "Element does not contain the expected message: " + foundText + "to be equal to " + msg);
    }

    @And("I type a random first name")
    public void i_type_a_random_first_name() {
        String randomFirstName = faker.name().firstName();
        browserManager.page.getByPlaceholder("First Name").fill(randomFirstName);
    }

    @And("I type a random last name")
    public void i_type_a_random_last_name() {
        String randomLastName = faker.name().lastName();
        browserManager.page.getByPlaceholder("Last Name").fill(randomLastName);
    }

    @And("I type a random email address")
    public void i_type_a_random_email_address() {
        String randomEmail = faker.internet().emailAddress();
        browserManager.page.getByPlaceholder("Email Address").fill(randomEmail);
    }
}
