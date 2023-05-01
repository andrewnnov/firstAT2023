package ru.bellint;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.GoogleAfterSearch;
import pages.GoogleBeforeSearch;

public class HomeTests extends BaseTest {

    @Test
    public void openGoogleAndFindWord() {

        chromeDriver.get("https://www.google.com/");
        GoogleBeforeSearch googleBeforeSearch = new GoogleBeforeSearch(chromeDriver);
        googleBeforeSearch.find("Гладиолус");
        GoogleAfterSearch googleAfterSearch = new GoogleAfterSearch(chromeDriver);
        Assertions.assertTrue(googleAfterSearch.getResults().stream().anyMatch(x->x.getText().contains("wikipedia.org")));
    }
}
