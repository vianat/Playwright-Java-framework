package pages;

import browser.BrowserManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

public class Base {
    private final BrowserManager browserManager;

    public Base(BrowserManager browserManager) {
        this.browserManager = browserManager;
    }

    protected BrowserManager getBrowserManager() {
        return browserManager;
    }

    public void waitAndClickByRole(String role, String name) {
        Locator element = browserManager.getPage()
                .getByRole(AriaRole.valueOf(role.toUpperCase()), new Page.GetByRoleOptions()
                    .setName(name));
        element.click();
    }

    public void waitAndClickBySelector(String selector) {
        browserManager.getPage()
                .waitForSelector(selector, new Page.WaitForSelectorOptions()
                    .setState(WaitForSelectorState.VISIBLE));
        browserManager.getPage().click(selector);
    }

    public void waitAndClick(Locator locator) {
        locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void navigate(String url) {
        browserManager.getPage().navigate(url);
    }

    public void fillField(String placeholder, String name) {
        browserManager.getPage().getByPlaceholder(placeholder).fill(name);
    }
}
