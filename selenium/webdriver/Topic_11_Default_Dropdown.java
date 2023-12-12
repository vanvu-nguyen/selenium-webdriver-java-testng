package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_11_Default_Dropdown {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_() {
    // Register phase
        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.xpath("//a[@class='ico-register']")).click();
        sleepInSecond(3);
        driver.findElement(By.id("gender-male")).click();
        String firstName = "vu", lastName = "nguyen", emailAdr = getEmailAddress(), company = "nguyen-corp", passWord = "123456789";
        driver.findElement(By.id("FirstName")).sendKeys(firstName);
        driver.findElement(By.id("LastName")).sendKeys(lastName);
        // Declaration for DoB date, month, year dropdown
        Select dateList = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
        Select monthList = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
        Select yearList = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
        // Select option in each dropdown
            // Declaration for DoB info
        String dateOB = "1", monthOB = "May", yearOB = "1980";
            // Select option
        dateList.selectByVisibleText(dateOB);
        monthList.selectByVisibleText(monthOB);
        yearList.selectByVisibleText(yearOB);
            // Check number of items for each dropdown
        Assert.assertEquals(dateList.getOptions().size(), 32);
        Assert.assertEquals(monthList.getOptions().size(), 13);
        Assert.assertEquals(yearList.getOptions().size(), 112);
        driver.findElement(By.id("Email")).sendKeys(emailAdr);
        driver.findElement(By.id("Company")).sendKeys(company);
        driver.findElement(By.id("Password")).sendKeys(passWord);
        driver.findElement(By.id("ConfirmPassword")).sendKeys(passWord);
        driver.findElement(By.id("register-button")).click();
        sleepInSecond(3);
        Assert.assertTrue(driver.getPageSource().contains("Your registration completed"));
    // Login
        driver.findElement(By.xpath("//a[@class='ico-login']")).click();
        sleepInSecond(3);
        driver.findElement(By.id("Email")).sendKeys(emailAdr);
        driver.findElement(By.id("Password")).sendKeys(passWord);
        driver.findElement(By.xpath("//button[@class='button-1 login-button']")).click();
        sleepInSecond(3);
    // Check account info
        driver.findElement(By.xpath("//div[@class='header']//a[text()='My account']")).click();
        Select profileDateList = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
        Select profileMonthList = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
        Select profileYearList = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
        Assert.assertEquals(profileDateList.getFirstSelectedOption().getText(), dateOB);
        Assert.assertEquals(profileMonthList.getFirstSelectedOption().getText(), monthOB);
        Assert.assertEquals(profileYearList.getFirstSelectedOption().getText(), yearOB);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    // Sleep function
    public void sleepInSecond (long timeInSecond) {
        try {
            Thread.sleep(timeInSecond*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // Random email address generator
    public String getEmailAddress() {
        Random randNum = new Random();
        return "vunguyen" + randNum.nextInt(99999) + "@gmail.com";
    }


}
