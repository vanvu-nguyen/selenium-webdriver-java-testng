package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.bidi.log.Log;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.Console;
import java.time.Duration;

public class Topic_09_WebElement_Practice {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    // Please manually test before coding for the TC

    @Test
    public void TC_01_Display_Check() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        if (driver.findElement(By.id("mail")).isDisplayed()) {
            driver.findElement(By.id("mail")).clear();
            driver.findElement(By.id("mail")).sendKeys("Automation Testing");
            System.out.println("Email is displayed");
        } else {
            System.out.println("Email is not displayed");
        }

        if (driver.findElement(By.id("edu")).isDisplayed()) {
            driver.findElement(By.id("edu")).clear();
            driver.findElement(By.id("edu")).sendKeys("Automation Testing");
            System.out.println("Education is displayed");
        } else {
            System.out.println("Education is not displayed");
        }

        if (driver.findElement(By.id("under_18")).isDisplayed()) {
            driver.findElement(By.id("under_18")).click();
            System.out.println("Under 18 radio button is displayed");
        } else {
            System.out.println("Under 18 radio button is not displayed");
        }

        if (driver.findElement(By.xpath("(//h5[text()='Name: User5']/ancestor::div)[last()]")).isDisplayed()) {
            System.out.println("Name: User5 is displayed");
        } else {
            System.out.println("Name: User5 is not displayed");
        }

        /*
        driver.get("https://automationfc.github.io/basic-form/index.html");
        Assert.assertTrue(driver.findElement(By.id("mail")).isDisplayed());
        System.out.println(driver.findElement(By.xpath("(//input[@id='mail']/preceding-sibling::label)[last()]")).getText() + " is displayed");
        driver.findElement(By.id("mail")).clear();
        driver.findElement(By.id("mail")).sendKeys("Automation Testing");
        Assert.assertTrue(driver.findElement(By.id("edu")).isDisplayed());
        System.out.println(driver.findElement(By.xpath("(//textarea[@id='edu']/preceding-sibling::label)[last()]")).getText() + " is displayed");
        driver.findElement(By.id("edu")).clear();
        driver.findElement(By.id("edu")).sendKeys("Automation Testing");
        Assert.assertTrue(driver.findElement(By.id("under_18")).isDisplayed());
        System.out.println(driver.findElement(By.xpath("(//input[@id='under_18']/preceding-sibling::label)[last()]")).getText() + " is displayed");
        driver.findElement(By.id("under_18")).click();
        Assert.assertFalse(driver.findElement(By.xpath("(//h5[text()='Name: User5']/ancestor::div)[last()]")).isDisplayed());
        System.out.println(driver.findElement(By.xpath("//h5[text()='Name: User5']")).getText() + " is not displayed");
        */
    }

    @Test
    public void TC_02_Enable_Check() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        if (driver.findElement(By.id("mail")).isEnabled()) {
            System.out.println("Email is enabled");
        } else {
            System.out.println("Email is disabled");
        }

        // ... if/else similarly for the rest elements

        /*
        driver.get("https://automationfc.github.io/basic-form/index.html");
        Assert.assertTrue(driver.findElement(By.id("mail")).isEnabled());
        Assert.assertTrue(driver.findElement(By.id("edu")).isEnabled());
        Assert.assertTrue(driver.findElement(By.id("under_18")).isEnabled());
        Assert.assertTrue(driver.findElement(By.id("job1")).isEnabled());
        Assert.assertTrue(driver.findElement(By.id("job2")).isEnabled());
        Assert.assertTrue(driver.findElement(By.id("development")).isEnabled());
        Assert.assertTrue(driver.findElement(By.id("slider-1")).isEnabled());

        Assert.assertFalse(driver.findElement(By.id("disable_password")).isEnabled());
        Assert.assertFalse(driver.findElement(By.id("radio-disabled")).isEnabled());
        Assert.assertFalse(driver.findElement(By.id("bio")).isEnabled());
        Assert.assertFalse(driver.findElement(By.id("job3")).isEnabled());
        Assert.assertFalse(driver.findElement(By.id("check-disbaled")).isEnabled());
        Assert.assertFalse(driver.findElement(By.id("slider-2")).isEnabled());
        */
    }

    @Test
    public void TC_03_Selected_Check() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.id("under_18")).click();
        driver.findElement(By.id("java")).click();
        Assert.assertTrue(driver.findElement(By.id("under_18")).isSelected());
        Assert.assertTrue(driver.findElement(By.id("java")).isSelected());
        driver.findElement(By.id("java")).click();
        Assert.assertFalse(driver.findElement(By.id("java")).isSelected());
    }

    @Test
    public void TC_04_MailChimp_Register() {
        driver.get("https://login.mailchimp.com/signup/");
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("vanvunguyen150491@gmail.com");
        driver.findElement(By.id("new_password")).clear();
        driver.findElement(By.id("new_password")).sendKeys("1111111");
        sleepInSecond(2);
        driver.findElement(By.id("new_password")).clear();
        driver.findElement(By.id("new_password")).sendKeys("11111111");
        sleepInSecond(2);
        driver.findElement(By.id("new_password")).clear();
        driver.findElement(By.id("new_password")).sendKeys("1111111$");
        sleepInSecond(2);
        driver.findElement(By.id("new_password")).clear();
        driver.findElement(By.id("new_password")).sendKeys("A111111$");
        sleepInSecond(2);
        driver.findElement(By.id("new_password")).clear();
        driver.findElement(By.id("new_password")).sendKeys("Aa11111$");
        sleepInSecond(2);

        // Nice to have, verify inline validation content for each case
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
