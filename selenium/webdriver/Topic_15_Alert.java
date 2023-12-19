package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Array;
import java.time.Duration;

public class Topic_15_Alert {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Accept_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        explicitWait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "I am a JS Alert");
        alert.accept();
        sleepInSecond(2);
        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked an alert successfully");
    }

    @Test
    public void TC_02_Confirm_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "I am a JS Confirm");
        alert.dismiss();
        sleepInSecond(2);
        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked: Cancel");
    }

    @Test
    public void TC_03_Prompt_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "I am a JS prompt");
        String enterContent = "vu nguyen";
        alert.sendKeys(enterContent);
        sleepInSecond(2);
        alert.accept();
        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(),"You entered: " + enterContent);
    }

    @Test
    public void TC_04_Authentication_Alert() {
        /*
        // Solution 1: Pass the id and password directly to url
        driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");
        sleepInSecond(3);
        Assert.assertEquals(driver.findElement(By.xpath("//p")).getText(), "Congratulations! You must have the proper credentials.");
        */

        // Solution 2: In case do not know the url
        driver.get("https://the-internet.herokuapp.com/");
        String originalAuthUrl = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
        accessAuthenticationSite(originalAuthUrl, "admin", "admin");
        Assert.assertEquals(driver.findElement(By.xpath("//p")).getText(), "Congratulations! You must have the proper credentials.");
    }

    @Test
    public void TC_05_Authentication_Alert_CDP() {
        // ... See the document (contains code)
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

    public void accessAuthenticationSite (String originalUrl, String userName, String passWord) {
        String[] splitUrl = originalUrl.split("//");
        String finalUrl = splitUrl[0] + "//" + userName + ":" + passWord + "@" + splitUrl[1];
        driver.get(finalUrl);
    }
}
