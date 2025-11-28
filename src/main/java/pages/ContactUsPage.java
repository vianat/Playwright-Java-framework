package pages;

import browser.BrowserManager;
import java.util.List;
import static org.testng.Assert.assertTrue;

public class ContactUsPage extends Base {
    public ContactUsPage(BrowserManager browserManager) {
        super(browserManager);
    }

    public void typeFirstName(String name) {
        fillField("First Name", name);
    }

    public void typeLastName(String name) {
        fillField("Last Name", name);
    }

    public void typeEmailAddress(String email) {
        fillField("Email Address", email);
    }

    public void typeComment(String comment) {
        fillField("Comments", comment);
    }

    public void clickSubmitButton() {
        waitAndClickBySelector("input[value='SUBMIT']");
    }

    public void clickResetButton() {
        waitAndClickBySelector("input[value='RESET']");
    }

    public void verifySuccessfulSubmissionMessage(String msg) {
        getBrowserManager().getPage().waitForSelector("//body");
        List<String> texts = getBrowserManager().getPage().locator("//body").allInnerTexts();
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
}
