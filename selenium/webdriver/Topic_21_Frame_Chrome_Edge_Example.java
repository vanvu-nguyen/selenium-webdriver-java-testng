package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_21_Frame_Chrome_Edge_Example {
    WebDriver driver;
    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Iframe() {
        driver.get("https://netbanking.hdfcbank.com/netbanking/");
        WebElement loginFrame = driver.findElement(By.xpath("//frame[@name='login_page']"));
        driver.switchTo().frame(loginFrame);
        driver.findElement(By.xpath("//input[@name='fldLoginUserId']")).sendKeys("vunguyenAutomationFC");
        driver.findElement(By.xpath("//a[@onclick='return fLogon();']")).click();
        sleepInSecond(5);
        Assert.assertTrue(driver.findElement(By.xpath("//input[@type='password']")).isDisplayed());
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
