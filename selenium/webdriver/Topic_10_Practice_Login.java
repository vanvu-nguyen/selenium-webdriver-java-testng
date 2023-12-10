package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_10_Practice_Login {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Empty_EmailAndPassword() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSecond(2);
        driver.findElement(By.id("send2")).click();
        Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(),"This is a required field.");
        Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(),"This is a required field.");
    }

    @Test
    public void TC_02_Invalid_Email() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSecond(2);
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("123456@123.456");
        driver.findElement(By.id("pass")).clear();
        driver.findElement(By.id("pass")).sendKeys("123456");
        driver.findElement(By.id("send2")).click();
        sleepInSecond(2);
        Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(),"Please enter a valid email address. For example johndoe@domain.com.");
    }

    @Test
    public void TC_03_LessThan6Chars_Password() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSecond(2);
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
        driver.findElement(By.id("pass")).clear();
        driver.findElement(By.id("pass")).sendKeys("123");
        driver.findElement(By.id("send2")).click();
        sleepInSecond(2);
        Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(),"Please enter 6 or more characters without leading or trailing spaces.");
    }

    @Test
    public void TC_04_CorrectEmail_IncorrectPassword() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSecond(2);
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
        driver.findElement(By.id("pass")).clear();
        driver.findElement(By.id("pass")).sendKeys("123123123");
        driver.findElement(By.id("send2")).click();
        sleepInSecond(2);
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(),"Invalid login or password.");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void sleepInSecond (long timeInSecond) {
        try {
            Thread.sleep(timeInSecond*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
