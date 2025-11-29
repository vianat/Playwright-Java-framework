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
    private static final ThreadLocal<Playwright> playwright = new ThreadLocal<>();
    private static final ThreadLocal<Browser> browser = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> context = new ThreadLocal<>();
    private static final ThreadLocal<Page> page = new ThreadLocal<>();
//    public Playwright playwright;         // used to create an instance of Chrome, FF, etc.
//    public Page page;                     // single tab or window in the browser
//    public BrowserContext context;        // is the isolated browser session
//    public Browser browser;               // represents the browser instance
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

    public Page getPage() {
        return page.get();
    }

    public void setPage(Page newPage) {
        page.set(newPage);
    }

    public BrowserContext getContext() {
        return context.get();
    }

    public  byte[] takeScreenshot() {
        if (page.get() != null) {
            return page.get().screenshot();
        }
        return new byte[0];
    }

    public void setUp() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        String browserType = properties.getProperty("browser", "chromium");
        String headlessType = properties.getProperty("headless", "false");
        int width = (int)screenSize.getWidth();
        int height = (int)screenSize.getHeight();

        try {
            playwright.set(Playwright.create());

            String browserType = System.getProperty("BROWSER");
            if (browserType == null || browserType.isEmpty()) {
                browserType = properties.getProperty("browser", "chromium");
            }

            BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions()
                    .setHeadless(Boolean.parseBoolean(headlessType))
                    .setArgs(java.util.Arrays.asList("--start-fullscreen"));
            Browser.NewContextOptions contextOptions = new Browser.NewContextOptions()
                    .setViewportSize(width, height);

            switch (browserType.toLowerCase()) {
                case "chromium":
                    browser.set(playwright.get().chromium().launch(launchOptions));
                    break;
                case "firefox":
                    browser.set(playwright.get().firefox().launch(launchOptions));
                    break;
                case "safari":
                case "webkit":
                    browser.set(playwright.get().webkit().launch(launchOptions));
                    break;
                default:
                    logger.warning("unsupported browser type " + browserType + " run on chromium");
                    browser.set(playwright.get().chromium().launch(launchOptions));
                    break;
            }

            context.set(browser.get().newContext(contextOptions));
            page.set(context.get().newPage());

            int navigationTimeout = Integer.parseInt(properties.getProperty("navigation.timeout", "30000"));
            int actionTimeout = Integer.parseInt(properties.getProperty("action.timeout", "15000"));
            page.get().setDefaultNavigationTimeout(navigationTimeout);
            page.get().setDefaultTimeout(actionTimeout);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to setup Playwright! ", e);
        }
    }

    public void tearDown() {
        try {
            if (page.get() != null) page.get().close();
            if (context.get() != null) context.get().close();
            if (browser.get() != null) browser.get().close();
            if (playwright.get() != null) playwright.get().close();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to close Playwright");
        }
    }
}
