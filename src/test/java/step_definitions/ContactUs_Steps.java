package step_definitions;

import browser.BrowserManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import context.PersonContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.datafaker.Faker;
import pages.ContactUsPage;

import java.awt.*;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.assertTrue;

public class ContactUs_Steps {
    public BrowserManager browserManager;
    private final Faker faker = new Faker();
    private final PersonContext personContext;
    private final ContactUsPage contactUsPage;

    public ContactUs_Steps(BrowserManager browserManager, PersonContext personContext, ContactUsPage contactUsPage) {
        this.browserManager = browserManager;
        this.personContext = personContext;
        this.contactUsPage = contactUsPage;
    }

    @And("I type a first name {string}")
    public void i_type_a_first_name(String name) {
        contactUsPage.typeFirstName(name);
    }

    @And("I type a last name {string}")
    public void i_type_a_last_name(String name) {
        contactUsPage.typeLastName(name);
    }

    @And("I type a email address {string}")
    public void i_type_a_email_address(String email) {
        contactUsPage.typeEmailAddress(email);
    }

    @And("I type a comment {string}")
    public void i_type_a_comment(String text) {
        contactUsPage.typeComment(text);
    }

    @And("I click on the submit button")
    public void i_click_on_the_submit_button() {
        contactUsPage.clickSubmitButton();
    }

    @Then("I should be presented with a successful contact us submission message {string}")
    public void i_should_be_presented_with_a_successful_contact_us_submission_message(String msg) {
        contactUsPage.verifySuccessfulSubmissionMessage(msg);
    }

    @And("I type a random first name")
    public void i_type_a_random_first_name() {
        String randomFirstName = faker.name().firstName();
        personContext.setRandomFirstName(randomFirstName);
        browserManager.getPage().getByPlaceholder("First Name").fill(randomFirstName);
    }

    @And("I type a random last name")
    public void i_type_a_random_last_name() {
        String randomLastName = faker.name().lastName();
        personContext.setRandomLastName(randomLastName);
        browserManager.getPage().getByPlaceholder("Last Name").fill(randomLastName);
    }

    @And("I type a random email address")
    public void i_type_a_random_email_address() {
        String randomEmail = faker.internet().emailAddress();
        personContext.setRandomEmail(randomEmail);
        browserManager.getPage().getByPlaceholder("Email Address").fill(randomEmail);
    }
}
