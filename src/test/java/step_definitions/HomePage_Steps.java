package step_definitions;

import browser.BrowserManager;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import io.cucumber.java.en.Given;
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

    @When("I click on the contact us button")
    public void i_click_on_the_contact_us_button() {
        homePage.clickContactUsButton();
    }

    @When("I click on the login portal button")
    public void i_click_on_the_login_portal_button() {
        homePage.clickLoginButton();
    }
}
