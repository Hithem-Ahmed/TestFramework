package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BingSearchPage {
    private final WebDriver driver;
    private final By searchBox = By.name("q");


    public BingSearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterSearchKeyword(String keyword) {
        WebElement searchInput = driver.findElement(searchBox);
        searchInput.sendKeys(keyword, Keys.RETURN);
    }
}