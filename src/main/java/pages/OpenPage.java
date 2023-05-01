package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OpenPage {

    protected WebDriver chromeDriver;

    private WebElement usdBuy;
    private WebElement usdSell;

    private WebElement euroBuy;
    private WebElement euroSell;

    public WebElement getUsdBuy() {
        return usdBuy;
    }

    public WebElement getUsdSell() {
        return usdSell;
    }

    public OpenPage(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        this.usdBuy = chromeDriver.findElement(By.xpath("(//span[@class='open-ui-text open-ui-text-theme-light s-size CurrencyExchangeItem_currency-exchange-item-value__pjtop'])[2]"));
        this.usdSell = chromeDriver.findElement(By.xpath("(//span[@class='open-ui-text open-ui-text-theme-light s-size CurrencyExchangeItem_currency-exchange-item-value__pjtop'])[3]"));
    }







}
