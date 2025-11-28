package pages;

import browser.BrowserManager;

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

    public void clickLoginButton() {
        getBrowserManager().setPage(getBrowserManager().getContext().waitForPage(() -> {
            waitAndClickByRole("link", "LOGIN PORTAL Login Portal");
        }));
        getBrowserManager().getPage().bringToFront();
    }
}
