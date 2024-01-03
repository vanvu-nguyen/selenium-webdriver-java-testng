package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class Topic_22_Tab_Window {
    WebDriver driver;
    Actions actions;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        actions = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        sleepInSecond(2);
        switchToByTitle("Google");
        driver.findElement(By.xpath("//textarea[@type='search']")).sendKeys("vnexpress");
        // actions.keyDown(Keys.ENTER).release().perform();
        sleepInSecond(5);
        switchToByTitle("Selenium WebDriver");
        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        sleepInSecond(2);
        switchToByTitle("Facebook – log in or sign up");
        driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
        sleepInSecond(2);
        switchToByTitle("Selenium WebDriver");
        driver.findElement(By.xpath("//a[text()='TIKI']")).click();
        System.out.println(driver.getTitle());
        switchToByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
        System.out.println(driver.getTitle());
        driver.findElement(By.xpath("//div[text()='Đồ Chơi - Mẹ & Bé']")).click();
        sleepInSecond(2);
        switchToByTitle("Selenium WebDriver");
        Set<String> allExistedTabIds = driver.getWindowHandles();
        for (String eId : allExistedTabIds) {
            driver.switchTo().window(eId);
            if (!driver.getTitle().equals("Selenium WebDriver")) {
                driver.close();
            }
        }
    }

    @AfterClass
    public void afterClass() {
        //driver.quit();
    }

    public void switchToByTitle(String expectedTitle) {
        Set<String> allTabIds = driver.getWindowHandles();
        for (String id: allTabIds) {
            driver.switchTo().window(id);
            if (driver.getTitle().equals(expectedTitle)) {
                break;
            }
        }
    }

    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}