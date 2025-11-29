package pages;

import browser.BrowserManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.testng.Assert;

public class LoginPage extends Base {
    private String alertText;

    public LoginPage(BrowserManager browserManager) {
        super(browserManager);
    }

    public void enterUsername(String username) {
        fillField("Username", username);
    }

    public void enterPassword(String password) {
        fillField("Password", password);
    }

    public void clickLoginButton() {
        getBrowserManager().getPage().onceDialog(dialog -> {
            System.out.println(String.format("Dialog message: %s", dialog.message()));
            alertText = dialog.message();
            dialog.accept();
        });
        waitAndClickByRole("BUTTON", "Login");
    }

    public void verifyAlertText(String expectedAlertText) {
        Assert.assertEquals(alertText, expectedAlertText, "The alert text does not match the expected text");
    }

}