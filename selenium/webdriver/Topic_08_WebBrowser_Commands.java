package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_08_WebBrowser_Commands {
    // Declaration for variable 'driver' with Selenium Interface 'WebDriver' that support the popular browser-drivers (Firefox, Chrome, Edge, Safari)
    WebDriver driver;

    // Declaration for TestNG annotation @BeforeClass which runs at the beginning of every testcase
    @BeforeClass
    public void beforeClass() {
        // Initialize browser-driver
        driver = new FirefoxDriver();
        // Set timeout for returning result of function findElement() and findElements. Syntax version 3, 2, 1
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        // ... and syntax for version 4
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    // Declaration for TestNG annotation @Test which runs as a function (testcase)
    @Test
    public void TC_01_() {
        // Select get() method in driver field (relate to WebDriver) to access a webpage by passing url
        driver.get("https://www.facebook.com/");
        // For closing the current tab on browser
        driver.close();
        // For closing browser
        driver.quit();
        /*
        Find the element with method findElement() of driver, then assign to a variable defined by Selenium interface WebElement
        Get the first element to implement on the next step if the result is more than 1 element.
        Return fail and stop the testcase when does not find any matched element
         */
        WebElement emailAddressTextBox = driver.findElement(By.id("email"));
        /*
        Find the elements with method findElements() of driver, then assign to a variable define by Selenium interface List<>
        Return the empty list when does not find any matched element and contuinue to implement the next step
         */
        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));

        // 2 methods findElement() and findElements() are influenced by function 'implicitWait'
    }

    // Declaration for TestNG annotation @AfterClass which runs finally of every testcase
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
