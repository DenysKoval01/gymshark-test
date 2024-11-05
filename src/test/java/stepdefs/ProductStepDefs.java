package stepdefs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageobjects.BagPage;
import pageobjects.HomePage;
import pageobjects.ProductDisplayPage;
import stepdefs.hooks.Hooks;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductStepDefs {

  private final WebDriver driver;
  private Long productId;

  public ProductStepDefs(){
    this.driver = Hooks.getDriver();
  }
  ProductDisplayPage productDisplayPage = new ProductDisplayPage();
  BagPage bagPage = new BagPage();
  @Given("the user is on a product page")
  public void theUserIsOnAProductPage() {
    driver.get("https://uk.gymshark.com/products/gymshark-speed-t-shirt-black-aw23");
    productId = 39654522814667L;
    productDisplayPage.checkThatProductDisplayPageVisible();
  }

  @When("adding the product to the Bag")
  public void addingTheProductToTheBag() {
    productDisplayPage.selectSmallSize();
    productDisplayPage.selectAddToBag();
  }

  @Then("the product should appear in the Bag")
  public void theProductShouldAppearInTheBag() {
    bagPage.checkThatBagPageLoaded();
    List<Long> variantIds = bagPage.getVariantIdsInBag();
    assertThat(variantIds).as("Expected product is in Bag").contains(productId);
  }

  @Given("there are products in the bag")
  public void thereAreProductsInTheBag(DataTable dataTable) throws InterruptedException {
    HomePage homePage  = new HomePage();
    List<String> products = dataTable.asList(String.class);
    products.forEach(homePage::searchProductAndAddProducts);
    bagPage.openBag();
  }
}
