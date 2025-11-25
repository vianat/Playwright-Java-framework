import com.microsoft.playwright.*;

import java.awt.*;

public class Start {
    public static void main(String[] args) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)screenSize.getWidth();
        int height = (int)screenSize.getHeight();

        try(Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(width, height));
            Page page = browserContext.newPage();
            page.navigate("https://www.webdriveruniversity.com/");
            System.out.println(page.title());
            page.close();
        }
    }
}
