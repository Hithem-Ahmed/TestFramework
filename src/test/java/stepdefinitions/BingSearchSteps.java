package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.BingSearchPage;
import java.net.MalformedURLException;
import java.net.URL;

import static org.testng.Assert.assertTrue;

public class BingSearchSteps {
    private static WebDriver driver;
    private BingSearchPage bingSearchPage;

    @Given("I open the Bing homepage")
    public void i_open_google_homepage() throws MalformedURLException {
        String useGrid = System.getProperty("useGrid", "false");
        if (useGrid.equalsIgnoreCase("true")) {
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), new DesiredCapabilities());
        } else {
            // Automatically downloads and sets up the latest ChromeDriver
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-debugging-port=9222");
            options.addArguments("--remote-allow-origins=*");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);
        }
        driver.get("https://www.bing.com");
        bingSearchPage = new BingSearchPage(driver);
    }

    @When("I search for {string}")
    public void i_search_for(String keyword) {
        bingSearchPage.enterSearchKeyword(keyword);
    }

    @Then("I should see results related to {string}")
    public void i_should_see_results_related_to(String keyword) {
        assertTrue(driver.getTitle().contains(keyword));
        driver.quit();
    }
}