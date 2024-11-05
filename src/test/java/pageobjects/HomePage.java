package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static utils.SeleniumCommands.getCommands;

public class HomePage {

    private static final By MAIN_SEARCH_PRODUCT = By.cssSelector("[data-locator-id='header-searchContainer-read']");
    private static final By ENTER_SEARCH_BUTTON = By.cssSelector("[data-locator-id='search-search-enter']");
    SearchPage searchPage = new SearchPage();


    public void searchProductAndAddProducts(String productName) {
        getCommands().waitForAndClickOnElementLocated(MAIN_SEARCH_PRODUCT);
        getCommands().waitForAndClickOnElementLocated(ENTER_SEARCH_BUTTON);
        getCommands().waitForAndSendKeys(ENTER_SEARCH_BUTTON, productName);
        getCommands().waitForAndSendKeys(ENTER_SEARCH_BUTTON, Keys.ENTER);
        searchPage.selectAddToBag();
    }
}
