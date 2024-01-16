package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_27_Wait_03_Implicitly_Wait {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.xpath("//button[text()='Start']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
