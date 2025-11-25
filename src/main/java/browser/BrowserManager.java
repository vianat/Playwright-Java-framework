package browser;

import com.microsoft.playwright.*;
import java.awt.*;

public class BrowserManager {
    public Playwright playwright;         // used to create an instance of Chrome, FF, etc.
    public Page page;                     // single tab or window in the browser
    public BrowserContext context; // is the isolated browser session
    public Browser browser;              // represents the browser instance

    public void setUp() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)screenSize.getWidth();
        int height = (int)screenSize.getHeight();

        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext(new Browser.NewContextOptions().setViewportSize(width, height));
        page = context.newPage();
    }

    public void tearDown() {
        if (page != null) page.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
}
