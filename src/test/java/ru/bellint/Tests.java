package ru.bellint;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import pages.BellAfterSearch;
import pages.BellBeforeSearch;

import java.util.List;

public class Tests extends BaseTest {

    @Test
    public void firstTestTitle() {
        chromeDriver.get("https://bellintegrator.ru/");
        String title = chromeDriver.getTitle();
        System.out.println(title);
        Assertions.assertTrue(title.contains("Bell Integrator"), "Title" + title + " не содержит Bell Integrator");
    }

    @Test
    public void secondTestFind() {
        chromeDriver.get("https://bellintegrator.ru/");
        chromeDriver.findElement(By.xpath("//img[contains(@src, 'close')]")).click();
        WebElement searchOpen = chromeDriver.findElement(By.id("search-open"));
        searchOpen.click();
        WebElement searchField = chromeDriver.findElement(By.name("search"));
        //searchField.sendKeys("RPA");
        searchField.sendKeys(Keys.ENTER);

        WebElement searchFieldTwo = chromeDriver.findElement(By.id("input-search"));
        searchFieldTwo.sendKeys("RPA");

        WebElement searchButton = chromeDriver.findElement(By.id("button-search"));
        searchButton.click();


        List<WebElement> resultSearch = chromeDriver.findElements(
                By.xpath("//div[@class='product-layout product-list col-xs-12']//p[@class='short__desc']"));

        System.out.println(resultSearch.size());
        resultSearch.forEach(x-> System.out.println(x.getText()));

        Assertions.assertTrue(resultSearch.stream().anyMatch(x->x.getText().contains("Кирилл Филенков")),
                "Статьи содержащие текст Кирилл Филенков не найдены");
    }

    @Test
    public void testPO() {
        chromeDriver.get("https://bellintegrator.ru/index.php?route=product/search&description=true&search=");
        BellBeforeSearch bellBeforeSearch = new BellBeforeSearch(chromeDriver);
        bellBeforeSearch.find("RPA");
        BellAfterSearch bellAfterSearch = new BellAfterSearch(chromeDriver);
        Assertions.assertTrue(bellAfterSearch.getResult().stream().anyMatch(x->x.getText().contains("Кирилл Филенков")),
                "Статьи содержащие текст Кирилл Филенков не найдены");


    }

}
