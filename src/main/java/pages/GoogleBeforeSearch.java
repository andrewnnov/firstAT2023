package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleBeforeSearch {

    protected WebDriver chromeDriver;
    private WebElement searchFieldG;
    private WebElement searchButtonG;

    public GoogleBeforeSearch(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        this.searchFieldG = chromeDriver.findElement(
                By.xpath("//textarea[@name='q']"));
        this.searchButtonG = chromeDriver.findElement(
                By.xpath("//div[@class='FPdoLc lJ9FBc']//input[@name='btnK']"));
    }

    public void find(String keysFind) {
        searchFieldG.click();
        searchFieldG.sendKeys(keysFind);
        searchButtonG.click();
    }
}
