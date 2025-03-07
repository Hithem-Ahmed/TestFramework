package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.BingSearchPage;
import utilities.DriverFactory;

import static org.testng.Assert.assertTrue;

public class BingSearchSteps {
    public static final String BING_URL = "https://www.bing.com/search?q=test";
    protected WebDriver driver;
    private BingSearchPage bingSearchPage;

    @Before
    public void init() throws Exception {
        driver = DriverFactory.getDriver();
    }

    @Given("I open the Bing homepage")
    public void i_open_google_homepage() {
        driver.get(BING_URL);
        bingSearchPage = new BingSearchPage(driver);
    }

    @When("I search for {string}")
    public void i_search_for(String keyword) {
        bingSearchPage.enterSearchKeyword(keyword);
    }

    @Then("I should see results related to {string}")
    public void i_should_see_results_related_to(String keyword) {
        assertTrue(driver.getTitle().contains(keyword));
    }

    @After
    public void afterTest() {
        if (driver != null) {
            driver.quit();
        }
    }
}