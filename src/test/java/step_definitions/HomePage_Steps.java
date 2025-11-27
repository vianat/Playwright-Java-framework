package step_definitions;

import browser.BrowserManager;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import java.awt.*;

public class HomePage_Steps {
    public BrowserManager browserManager;

    public HomePage_Steps(BrowserManager browserManager) {
        this.browserManager = browserManager;
    }

    @Given("I navigate {string} homepage")
    public void navigate_to(String url) {
        browserManager.getPage().navigate(url);
    }

    @When("I click on the contact us button")
    public void i_click_on_the_contact_us_button() {
        browserManager.setPage(browserManager.getContext().waitForPage(() -> {
            browserManager.getPage().getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("CONTACT US Contact Us Form")).click();
        }));
        browserManager.getPage().bringToFront();
        browserManager.getPage().getByPlaceholder("First Name").fill("Joe");
    }

    @When("I click on the login portal button")
    public void i_click_on_the_login_portal_button() {
        browserManager.setPage(browserManager.getContext().waitForPage(() -> {
            browserManager.getPage().getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("LOGIN PORTAL Login Portal")).click();
        }));

        browserManager.getPage().bringToFront();
    }
}
