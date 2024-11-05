package stepdefs.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.PropertiesReader;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class Hooks {

  private static WebDriver driver;
  private final Properties properties = PropertiesReader.getPropertiesFromFile("test.properties");

  @Before
  public void setup() {
    if (driver == null) {
      driver = new ChromeDriver();
      driver.get(properties.get("site.name").toString());
      driver.manage().addCookie(new Cookie("gs-headless-locale-production", properties.get("site.language").toString()));
      driver.manage().addCookie(new Cookie("OptanonAlertBoxClosed", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
      driver.navigate().refresh();
      driver.manage().window().maximize();
    }
  }

  @After
  public void teardown() {
    if (driver != null) {
      driver.quit();
      driver = null;
    }
  }

  public static WebDriver getDriver() {
    return driver;
  }

}
