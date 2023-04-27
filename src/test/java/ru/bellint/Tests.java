package ru.bellint;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import pages.BellAfterSearch;
import pages.BellBeforeSearch;
import pages.PageFactoryBell;

import java.util.List;

public class Tests extends BaseTest {

    @Feature("Проверка тайтла")
    @Test
    public void firstTestTitle() {
        chromeDriver.get("https://bellintegrator.ru/");
        String title = chromeDriver.getTitle();
        System.out.println(title);
        Assertions.assertTrue(title.contains("Bell Integrator"), "Title" + title + " не содержит Bell Integrator");
    }

    @Feature("Проверка результатов поиска")
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

    @Feature("Проверка результатов поиска")
    @Test
    public void testPO() {
        chromeDriver.get("https://bellintegrator.ru/index.php?route=product/search&description=true&search=");
        BellBeforeSearch bellBeforeSearch = new BellBeforeSearch(chromeDriver);
        bellBeforeSearch.find("RPA");
        BellAfterSearch bellAfterSearch = new BellAfterSearch(chromeDriver);
        Assertions.assertTrue(bellAfterSearch.getResult().stream().anyMatch(x->x.getText().contains("Кирилл Филенков")),
                "Статьи содержащие текст Кирилл Филенков не найдены");
    }

    @Feature("Проверка результатов поиска")
    @DisplayName("Проверка результатов поиска с помощью пейдж обжект")
    @ParameterizedTest(name = "{displayName} {arguments}")
    @CsvSource({"RPA, Кирилл Филенков", "Нагрузочное тестирование, Сергей Минаев"})
    public void testPOParameter(String keyWords, String results) {
        chromeDriver.get("https://bellintegrator.ru/index.php?route=product/search&description=true&search=");
        BellBeforeSearch bellBeforeSearch = new BellBeforeSearch(chromeDriver);
        bellBeforeSearch.find(keyWords);
        BellAfterSearch bellAfterSearch = new BellAfterSearch(chromeDriver);
        Assertions.assertTrue(bellAfterSearch.getResult().stream().anyMatch(x->x.getText().contains(results)),
                "Статьи содержащие текст" + results + " не найдены");
    }

    @Feature("Проверка результатов поиска")
    @Test
    public void testPF() {
        chromeDriver.get("https://bellintegrator.ru/index.php?route=product/search&description=true&search=");
        PageFactoryBell pageFactoryBell = PageFactory.initElements(chromeDriver, PageFactoryBell.class);
        pageFactoryBell.find("RPA");
        Assertions.assertTrue(pageFactoryBell.getResults().stream().anyMatch(x->x.getText().contains("Кирилл Филенков")),
                "Статьи содержащие текст Кирилл Филенков не найдены");
    }





}
