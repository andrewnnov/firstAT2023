package ru.bellint;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.GoogleAfterSearch;
import pages.GoogleBeforeSearch;
import pages.OpenPage;

import java.util.List;

public class HomeTests extends BaseTest {

    @Test
    public void openGoogleAndFindWord() {

        chromeDriver.get("https://www.google.com/");
        GoogleBeforeSearch googleBeforeSearch = new GoogleBeforeSearch(chromeDriver);
        googleBeforeSearch.find("Гладиолус");
        GoogleAfterSearch googleAfterSearch = new GoogleAfterSearch(chromeDriver);
        Assertions.assertTrue(googleAfterSearch.getResults().stream().anyMatch(x->x.getText().contains("wikipedia.org")),
                "В результатах поиска нет сайта википедии");
    }


    @Test
    public void openBankOtrkPage() throws InterruptedException {
        chromeDriver.get("https://www.google.com/");
        GoogleBeforeSearch googleBeforeSearch = new GoogleBeforeSearch(chromeDriver);
        googleBeforeSearch.find("Открытие");
        GoogleAfterSearch googleAfterSearch = new GoogleAfterSearch(chromeDriver);
        List<WebElement> results= googleAfterSearch.getResults();

        Assertions.assertTrue(results.stream().anyMatch(x->x.getText().contains("https://www.open.ru")),
                "В результатах поиска нет ссылки на банк Открытие");

        chromeDriver.get("https://www.open.ru");
        Thread.sleep(1000);

        OpenPage page = new OpenPage(chromeDriver);
        String usdConvert = page.getUsdBuy().getText();
        String usdConvertSell = page.getUsdSell().getText();

        double usdBuyD = Double.parseDouble(usdConvert);
        double usdSellD = Double.parseDouble(usdConvertSell);

        System.out.println(usdSellD - usdBuyD > 0);
    }

}
