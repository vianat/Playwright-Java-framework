package step_definitions;

import browser.BrowserManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pages.Base;
import pages.ProductsPage;

import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.assertTrue;

public class Products_Steps extends Base {
    public BrowserManager browserManager;
    private final ProductsPage productsPage;

    public Products_Steps(BrowserManager browserManager, ProductsPage productsPage) {
        super(browserManager);
        this.productsPage = productsPage;
    }

    @Then("verify product characteristics should contain:")
    public void verify_product_characteristics_should_contain(DataTable expectedData) {
        List<String> expected = expectedData.asList(String.class);
        Locator liElements = getBrowserManager().getPage().locator("(//div[contains(@class,'x-tabs-panels')]//div)[4]//li");
        liElements.first().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

        List<String> actual = liElements.allTextContents()
                .stream()
                .map(String::trim)
                .collect(Collectors.toList());

        for (String expectedText : expected) {
            assertTrue(actual.contains(expectedText), String.format("Expected text '%s' not found", expectedText));
        }
    }

    @And("click on {string} tab")
    public void clickOnTab(String tab) {
        waitAndClickByRole("TAB", tab);
    }

    @And("Find the product {string} in the catalog in the {string} category")
    public void findTheProductInTheCatalogInTheCategory(String product, String category) {
        getBrowserManager().getPage().getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(category).setExact(true)).click();
        waitAndClickByRole("LINK", product);
    }
}
