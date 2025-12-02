package step_definitions;

import browser.BrowserManager;
import com.microsoft.playwright.Locator;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pages.FindARepPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class FindARep_Steps {
    public BrowserManager browserManager;
    private final FindARepPage findARepPage;

    public FindARep_Steps(BrowserManager browserManager, FindARepPage findARepPage) {
        this.browserManager = browserManager;
        this.findARepPage = findARepPage;
    }

    @And("fill Find A Representative Form with values: {string} {string} {string} {string}")
    public void fill_find_a_representative_form_with_values(String product, String country, String state, String zip) {
        findARepPage.fillFindARepresentativeForm(product, country, state, zip);
    }

    @Then("verify Texas has {string} local representations")
    public void verify_texas_has_local_representations(String expected) {
        Locator resultElements = browserManager.getPage().locator(".fwpl-result");
        assertEquals(Integer.parseInt(expected), resultElements.count());
        assertTrue(resultElements.all().stream().allMatch(Locator::isVisible));
    }
}