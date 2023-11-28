package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_02_Selenium_Locator {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://demo.nopcommerce.com/register");
    }

    /*
    1. Selenium locator = html attribute
    2. Selenium version 1.x, 2.x, 3.x, 4.x has 8 Locator types:
        - id, class, name (=html attribute)
        - css, xpath
        - tag name (= html tag)
        - linktext, partial linktext (= html <a> tag)
    3. Selenium 4.x has additional locators:
        - Class, Relative Locator
        - above, below, near, leftOf, rightOf
    4. TestNG set order to run testcase base on function name (0~9 then a~z)
    5. Java data type has 2 groups of type:
        - Primitive types:
            + Integer:
                * byte: byte bNumber = 1
                * short: short sNumber =
                * int: int iNumber =
                * long: long lNumber =
            + Real number:
                * float: float fNumber = f9.999999
                * double: double dNumber =d888.99999999
            + Char: char c = '$'; (only 1 char, only accept '' not "", allow any char in ASCII)
            + Boolean: boolean male = true
        - Non-primitive types:
            + String: string name = "tam"
            + Class: FirefoxDriver firefoxDriver = new FirefoxDriver ()
            + Interface: Webdriver driver
            + Object: Object name = "Automation FC"
            + Java Collection: (learn later)
                * List
                * Set
                * Queue
            + Array: int[] studentAge = {5, 10, 15} / string[] studentName = {"vu", "tam", "uyen"}
     6. Syntax for variable declaration: <access modifier> <data type> = <variabe name>;
        <access modifier>: public/private/protected/default (default value if not declare access modifier)
     7. In case Selenium finds more than 1 element with any locator, the first element will be executed function.
     8. Catching element must be returned the one and only element.
     */

    @Test
    public void TC_01_ID() {
        // Find element by html id attribute
        driver.findElement(By.id("FirstName"));
    }

    @Test
    public void TC_02_Class() {
        // Find element by html class attribute
        driver.findElement(By.className("header-logo"));
    }

    @Test
    public void TC_03_Name() {
        // Find element by html name attribute
        driver.findElement(By.name("DateOfBirthDay"));
    }

    @Test
    public void TC_04_Tagname() {
        // Find element by html tag name
        driver.findElement(By.tagName("input"));
    }

    @Test
    public void TC_05_LinkText() {
        // Find element by html <a> tag content (<a>Shipping & returns</a>)
        driver.findElement(By.linkText("Shipping & returns"));
    }

    @Test
    public void TC_06_Partial_LinkText() {
        // Find element by a part of html <a> tag content (<a>Apply for vendor account</a>)
        driver.findElement(By.partialLinkText("for vendor"));
        driver.findElement(By.partialLinkText("vendor account"));
        driver.findElement(By.partialLinkText("Apply for vendor"));
    }

    @Test
    public void TC_07_Css() {
        // Find element by html css selector id
        driver.findElement(By.cssSelector("#FirstName"));
        // Find element by html css selector class
        driver.findElement(By.cssSelector(".page-title"));
        // Find element by html css selector name
        driver.findElement(By.cssSelector("input[name='FirstName']"));
        // Find element by html css selector tagname
        driver.findElement(By.cssSelector("input"));
        // Find element by html css selector link (customer/addresses)
        driver.findElement(By.cssSelector("a[href='/customer/addresses']"));
        // Find element by html css selector partial link (customer/addresses)
        driver.findElement(By.cssSelector("a[href*='addresses']"));
    }

    @Test
    public void TC_08_XPath() {
        // Find element by xpath id
        driver.findElement(By.xpath("//input[@id='FirstName']"));
        // Find element by xpath class
        driver.findElement(By.xpath("//div[@class='page-title']"));
        // Find element by xpath name
        driver.findElement(By.xpath("//input[@name='FirstName']"));
        // Find element by xpath tagname
        driver.findElement(By.xpath("//input"));
        // Find element by xpath link (customer/addresses)
        driver.findElement(By.xpath("//a[@href='/customer/addresses']"));
        driver.findElement(By.xpath("//a[text()='Addresses']"));
        // Find element by xpath partial link (customer/addresses)
        driver.findElement(By.xpath("//a[contains(@href, 'addresses')]"));
        driver.findElement(By.xpath("//a[contains(text(), 'Addresses')]"));
    }

    @AfterClass
    public void afterClass() {
        // driver.quit();
    }
}
