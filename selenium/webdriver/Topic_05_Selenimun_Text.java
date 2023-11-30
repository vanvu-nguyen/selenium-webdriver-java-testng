package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_05_Selenimun_Text {
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
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_() {

        // 1. Pass a string to XPath to check display:
            // Using text(): recommend to use because it's the most exact
        driver.get("https://automationfc.github.io/basic-form/");
        driver.findElement(By.xpath("//h1[text()='Selenium WebDriver API']")).isDisplayed();

            // Using contains(): not recommend to use because sometimes it catchs the wrong element.
        driver.findElement(By.xpath("//p[contains(text(),'Mail Personal or Business Check')]")).isDisplayed();

        // 2. Get text first, compare later
        String text = driver.findElement(By.xpath("//h5[@id='nine']/p[1]")).getText();
        Assert.assertTrue(text.contains("Mail Personal or Business Check"));
        Assert.assertTrue(text.contains("Cashier's Check or money order to"));
        Assert.assertTrue(text.contains("Mail Personal or Business Check, Cashier's Check or money order to:"));

        // For nested text,getText() function allow get all text content inside the html tag (all text content in parent node and child node)
        String nestedText = driver.findElement(By.xpath("//h5[@id='nested']")).getText();
        // Then compare the variable with the expected string
        Assert.assertEquals(nestedText, "Hello World! (Ignore Me) @04:45 PM");

        // In case the text content has both parentheses (') and quotes ("), we have to split and then the content by using function concat()
            // Solution no.1: Recommend to use because of beautiful code
        String concatText = driver.findElement(By.xpath("//span[@class='concat']")).getText();
        Assert.assertEquals(concatText, "Hello \"John\", What's happened?");

            // Solution no.2: Not recommend because of confusing code
        driver.findElement(By.xpath("//span[text()=concat('Hello \"John\", What', \"'s happened?\")]")).isDisplayed();
    }

    @AfterClass
    public void afterClass() {

        // driver.quit();
    }
}
