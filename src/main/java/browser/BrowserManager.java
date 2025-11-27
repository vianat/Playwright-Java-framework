package browser;

import com.microsoft.playwright.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BrowserManager {
    public Playwright playwright;         // used to create an instance of Chrome, FF, etc.
    public Page page;                     // single tab or window in the browser
    public BrowserContext context; // is the isolated browser session
    public Browser browser;              // represents the browser instance
    public Properties properties;
    private static final Logger logger = Logger.getLogger(BrowserManager.class.getName());

    public BrowserManager() {
        properties = new Properties();
        // browser instance run settings, located src/main/resources/config.properties by default
        Path configPath = Paths.get(System.getProperty("config.path",
                Paths.get(System.getProperty("user.dir"),"src", "main", "resources", "config.properties").toString()));
        try(InputStream input = Files.newInputStream(configPath)) {
            properties.load(input);
        }catch (IOException e) {
            logger.log(Level.SEVERE, "failed to load prop file");
        }
    }

    public  byte[] takeScreenshot() {
        if (page != null) {
            return page.screenshot();
        }
        return new byte[0];
    }

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
