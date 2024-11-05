package stepdefs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import pageobjects.BagPage;
import utils.StringUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static pageobjects.BagPage.REMOVE_ITEM;
import static utils.SeleniumCommands.getCommands;

public class BagStepDefs {
    BagPage bagPage = new BagPage();

    @When("I remove a product {string}")
    public void removeProductFromBag(String productName) {
        bagPage.removeItemFromBag(productName);
    }

    @Then("the product is removed from the bag {string}")
    public void productIsRemovedFromTheBag(String productName) {
        assertThat(getCommands().waitForAndGetNotVisibleElementLocated(By.xpath(String.format(REMOVE_ITEM, productName)))).isTrue();

    }

    @When("I choose quantity {int} to {string}")
    public void chooseQuantity(int value, String productName) {
        bagPage.chooseQtyOption(productName, value);
    }

    @Then("product quantity is equal to value {string} in product {string}")
    public void productQuantityIsIncreased(String value, String productName) {
        String[] qtyText = StringUtils.splitText(bagPage.getTextQtyElement(productName));
        assertThat(qtyText[1]).isEqualTo(value);
    }

}
