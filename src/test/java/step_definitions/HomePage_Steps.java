package step_definitions;

import browser.BrowserManager;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Base;
import pages.HomePage;

import java.awt.*;

public class HomePage_Steps {
    public BrowserManager browserManager;
    private final HomePage homePage;

    public HomePage_Steps(BrowserManager browserManager, HomePage homePage) {
        this.browserManager = browserManager;
        this.homePage = homePage;
    }

    @Given("I navigate {string} homepage")
    public void navigate_to(String url) {
        homePage.navigate(url);
    }

    @Then("Accept cookies")
    public void accept_cookies() {
        homePage.acceptCookies();
    }

    @When("I click on the contact us button")
    public void i_click_on_the_contact_us_button() {
        homePage.clickContactUsButton();
    }

    @When("I click on the login portal button")
    public void i_click_on_the_login_portal_button() {
        homePage.clickLoginButton();
    }

    @When("I click Find a Representative link")
    public void clickFindARepresentative() {
        homePage.clickFindARepresentative();
    }

    @Given("open Products Catalog")
    public void open_products_catalog() {
        homePage.openProductsCatalog();
    }
}
