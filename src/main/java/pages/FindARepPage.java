package pages;

import browser.BrowserManager;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class FindARepPage extends Base {
    public FindARepPage(BrowserManager browserManager) {
        super(browserManager);
    }

    public void fillFindARepresentativeForm(String product, String country, String state, String zip) {
        getBrowserManager().getPage().getByText("THERMON PRODUCT / SOLUTION OF").nth(1).click();
        getBrowserManager().getPage().locator(".fs-option:has-text('"+ product +"')").click();
        getBrowserManager().getPage().getByText("COUNTRY").nth(1).click();
        getBrowserManager().getPage().locator(".fs-option:has-text('"+ country +"')").click();
        getBrowserManager().getPage().getByText("State").nth(4).click();
        getBrowserManager().getPage().locator(".fs-option:has-text('"+ state +"')").click();
        getBrowserManager().getPage().getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter a Zip Code")).click();
        getBrowserManager().getPage().getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter a Zip Code")).fill(zip);
        getBrowserManager().getPage().getByLabel("Submit").click();
        getBrowserManager().getPage().waitForTimeout(4000);
    }
}