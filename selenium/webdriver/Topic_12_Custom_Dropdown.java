package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_12_Custom_Dropdown {
    WebDriver driver;

    // Initialize for a WebDriverWait variable
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        // Declaration variable 'explicitWait' with the waiting duration for specified condition (will be set after action click dropdown)
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_() {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
        // Find the custom dropdown element then click on it
        driver.findElement(By.id("number-button")).click();
        // Wait until all items of dropdown listed on html
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//ul[@id='number-menu']/li")));
        // Declaration variable for dropdown all items
        List<WebElement> allItems = driver.findElements(By.xpath("//ul[@id='number-menu']/li"));
        // Looping each item using For Each function
        for (WebElement item : allItems) {
            String itemText = item.getText();
            System.out.println(itemText);
            if (itemText.equals("15")) {
                item.click();
                break;
            }
        }
    }

    @AfterClass
    public void afterClass() {
        // driver.quit();
    }
}
