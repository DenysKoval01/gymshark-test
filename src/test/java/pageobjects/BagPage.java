package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static stepdefs.hooks.Hooks.getDriver;
import static utils.SeleniumCommands.getCommands;
import static utils.StringUtils.extractVariantIDFromString;

public class BagPage {

    private static final By BAG_PAGE = By.cssSelector("[data-locator-id='miniBag-component']");
    private static final By BAG_ITEMS = By.cssSelector("[data-locator-id^='miniBag-miniBagItem']");
    public static final String GS_LOCATOR_ATTRIBUTE = "data-locator-id";
    public static final By CLOSE_BAG_BUTTON = By.cssSelector("[data-locator-id='miniBag-closeButton-select']");
    public static final String REMOVE_ITEM = "//h2[contains(text(), '%s')]" +
            "/ancestor::div[contains(@class, 'product-card_content-container')]" +
            "//button[contains(@class, 'product-card_remove-button')]//i[contains(@class, 'icon-delete')]";
    private final By BAG_BUTTON = By.xpath("//button[contains(@class, 'cart-trigger_minicartTrigger')]");
    private static final String CHOOSE_OPTION = "//h2[contains(text(), '%s')]" +
            "/ancestor::div[contains(@class, 'product-card_content-container')]" +
            "//select[contains(@id, 'qty-dropdown')]/option[@value='%s']";
    public static final String QTY_COUNTER = "//h2[contains(text(), '%s')]" +
            "/ancestor::div[contains(@class, 'product-card_content-container')]" +
            "//span[contains(@class, 'qty-selector_text')]";

    private static final By FREE_SHIPPING_ELEMENT = By.xpath("//div[contains(@class, 'shipping-message_success-text')]//span[contains(@class, 'shipping-message_strong') and contains(text(), \"You've qualified for\")]");
    public BagPage() {
    }

    public void checkThatBagPageLoaded() {
        getCommands().waitForAndGetVisibleElementLocated(BAG_PAGE);
    }

    public List<Long> getVariantIdsInBag() {
        return getBagItems().stream()
                .map(this::getVariantId)
                .collect(Collectors.toList());
    }

    private List<WebElement> getBagItems() {
        return getCommands().waitForAndGetAllVisibleElementsLocated(BAG_ITEMS);
    }

    private long getVariantId(WebElement bagItem) {
        return extractVariantIDFromString(getCommands().getAttributeFromElement(bagItem, GS_LOCATOR_ATTRIBUTE));
    }

    public void pressCloseBagButton() {
        getCommands().waitForAndClickOnElementLocated(CLOSE_BAG_BUTTON);
    }

    public void removeItemFromBag(String product) {
        getCommands().waitForAndClickOnElementLocated(By.xpath(String.format(REMOVE_ITEM, product)));
    }

    public void openBag() {
        WebElement element = getCommands().waitForAndGetVisibleElementLocated(BAG_BUTTON);
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", element);
    }

    public void chooseQtyOption(String productName,int value){
        getCommands().waitForAndClickOnElementLocated(By.xpath(String.format(CHOOSE_OPTION, productName,value)));
    }

    public String getTextQtyElement(String product){
        getCommands().waitForAndGetVisibleElementLocated(FREE_SHIPPING_ELEMENT);
        return getCommands().getTextFromElement(By.xpath(String.format(QTY_COUNTER, product)));
    }
}
