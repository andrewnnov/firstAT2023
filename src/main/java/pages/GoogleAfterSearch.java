package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class GoogleAfterSearch {

    WebDriver chromeDriver;
    private List<WebElement> results;


    public GoogleAfterSearch(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;

    }

    //WebDriverWait webDriverWait = new WebDriverWait(chromeDriver, 120);


    public List<WebElement> getResults() {
//        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
//                By.xpath("//div[@id='result-stats']")));
        results = chromeDriver.findElements(
                By.xpath("//div[@class='MjjYud']//h3/parent::a"));
        return results;
    }
}
