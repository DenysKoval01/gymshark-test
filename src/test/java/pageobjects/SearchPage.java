package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.SeleniumCommands.getCommands;

public class SearchPage {

    public static final By SEARCH_PAGE = By.xpath("//p[contains(@class, 'pagination')]");
    private static final By FOUNDED_PRODUCTS = By.xpath("//*[@aria-label='product']");

    ProductDisplayPage productDisplayPage = new ProductDisplayPage();
    BagPage bagPage = new BagPage();

    public void checkThatSearchPageLoaded() {
        getCommands().waitForAndGetVisibleElementLocated(SEARCH_PAGE);
    }

    public void selectAddToBag() {
        checkThatSearchPageLoaded();
        List<WebElement> listOfProducts = getFoundedProducts();
        assertThat(listOfProducts).isNotEmpty();
        listOfProducts.getFirst().click();
        productDisplayPage.selectSmallSize();
        productDisplayPage.selectAddToBag();
        bagPage.pressCloseBagButton();
    }

    private List<WebElement> getFoundedProducts() {
        return getCommands().waitForAndGetAllVisibleElementsLocated(FOUNDED_PRODUCTS);
    }
}
