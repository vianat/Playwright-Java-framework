package step_definitions;

import context.PersonContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.datafaker.Faker;
import pages.ContactUsPage;
import pages.LoginPage;

public class Login_Steps {
    private final Faker faker = new Faker();
    private final PersonContext personContext;
    private final ContactUsPage contactUsPage;
    private final LoginPage loginPage;

    public Login_Steps(PersonContext personContext, ContactUsPage contactUsPage, LoginPage loginPage) {
        this.personContext = personContext;
        this.contactUsPage = contactUsPage;
        this.loginPage = loginPage;
    }

    //Random Data - Data Faker
    @And("I type a random first name")
    public void i_type_a_random_first_name() {
        String randomFirstName = faker.name().firstName();
        personContext.setRandomFirstName(randomFirstName); // Store in PersonContext
        contactUsPage.typeFirstName(randomFirstName);
    }

    @And("I type a random last name")
    public void i_type_a_random_last_name() {
        String randomLastName = faker.name().lastName();
        personContext.setRandomLastName(randomLastName); // Store in PersonContext
        contactUsPage.typeLastName(randomLastName);
    }

    @And("I type a random email address")
    public void i_type_a_random_email_address() {
        String randomEmail = faker.internet().emailAddress();
        personContext.setRandomEmail(randomEmail);
        contactUsPage.typeEmailAddress(randomEmail);
    }

    @And("I type a random comment")
    public void i_type_a_random_comment() {
        contactUsPage.typeComment("Hi, please can you contact me?" +
                "\nMy details: " + personContext.getRandomFirstName() + " " + personContext.getRandomLastName() + " " +
                personContext.getRandomEmail());
    }

    //Scenario outlines:
    @And("I type a first name {word} and a last name {word}")
    public void i_type_a_first_name_john_and_a_last_name_jones(String firstName, String lastName) {
        contactUsPage.typeFirstName(firstName);
        contactUsPage.typeLastName(lastName);
    }

    @And("I type a email address {string} and a comment {string}")
    public void i_type_a_email_address_and_a_comment(String email, String comment) {
        contactUsPage.typeEmailAddress(email);
        contactUsPage.typeComment(comment);
    }

    @And("I type a username {word}")
    public void i_type_a_username(String username) {
        loginPage.enterUsername(username);
    }

    @And("I type a password {word}")
    public void i_type_a_password(String password) {
        loginPage.enterPassword(password);
    }

    @And("I click on the login button")
    public void i_click_on_the_login_button() {
        loginPage.clickLoginButton();
    }

    @Then("I should be presented with an alert box which contains text {string}")
    public void i_should_be_presented_with_an_alert_box_which_contains_text(String expectedAlertText) {
        loginPage.verifyAlertText(expectedAlertText);
    }
}
