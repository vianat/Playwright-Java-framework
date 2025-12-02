package pages;

import browser.BrowserManager;
import io.cucumber.java.en.Then;

public class HomePage extends Base {
    public HomePage(BrowserManager browserManager) {
        super(browserManager);
    }

    public void navigateToHomePage() {
        navigate("https://www.webdriveruniversity.com/");
    }

    public void clickContactUsButton() {
        getBrowserManager().setPage(getBrowserManager().getContext().waitForPage(() -> {
            waitAndClickByRole("link", "CONTACT US Contact Us Form");
        }));
        getBrowserManager().getPage().bringToFront();
    }

    public void acceptCookies() {
        waitAndClickByRole("BUTTON", "Accept all");
    }

    public void clickLoginButton() {
        getBrowserManager().setPage(getBrowserManager().getContext().waitForPage(() -> {
            waitAndClickByRole("link", "LOGIN PORTAL Login Portal");
        }));
        getBrowserManager().getPage().bringToFront();
    }

    public void clickFindARepresentative() {
        waitAndClickByRole("link", "FIND A REPRESENTATIVE");
        getBrowserManager().getPage().bringToFront();
    }

    public void openProductsCatalog() {
        waitAndClickByRole("LINK", "PRODUCTS");
        getBrowserManager().getPage().bringToFront();
    }
}
