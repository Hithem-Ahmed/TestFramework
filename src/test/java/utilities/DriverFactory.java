package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.experimental.UtilityClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

@UtilityClass
public class DriverFactory {

    private static final String CHROME = "chrome";
    private static final String FIREFOX = "firefox";
    private static final String EDGE = "edge";

    public static WebDriver getDriver() throws MalformedURLException {
        String browser = System.getProperty("browser", "chrome");
        String useGrid = System.getProperty("useGrid", "false");
        String gridUrlString = System.getProperty("gridURL", "http://localhost:4444/wd/hub");

        WebDriver driver;

        if (useGrid.equalsIgnoreCase("true")) {
            URL gridUrl = URI.create(gridUrlString).toURL();
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(browser.toLowerCase());
            driver = new RemoteWebDriver(gridUrl, capabilities);
        } else {
            switch (browser.toLowerCase()) {
                case CHROME:
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--remote-debugging-port=9222");
                    options.addArguments("--remote-allow-origins=*");
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(options);
                    break;
                case FIREFOX:
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("--remote-debugging-port=9222");
                    firefoxOptions.addArguments("--remote-allow-origins=*");
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver(firefoxOptions);
                    break;
                case EDGE:
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("--remote-debugging-port=9222");
                    edgeOptions.addArguments("--remote-allow-origins=*");
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver(edgeOptions);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid browser: " + browser);
            }
        }

        driver.manage().window().maximize();
        return driver;
    }

}
