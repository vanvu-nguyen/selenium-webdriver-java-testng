package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_17_Popup_1 {
    WebDriver driver;
    Actions actions;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        actions =new Actions(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Fixed_Popup_InDOM_1() {
        driver.get("https://ngoaingu24h.vn/");
        actions.click(driver.findElement(By.xpath("//button[@class='login_ icon-before']"))).perform();
        sleepInSecond(2);
        Assert.assertTrue(driver.findElement(By.xpath("//a[@onclick='onForgetPassword(this)']")).isDisplayed());
        driver.findElement(By.id("account-input")).sendKeys("automationfc");
        driver.findElement(By.id("password-input")).sendKeys("automationfc");
        driver.findElement(By.xpath("//button[@onclick='onLoginUser(this)']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//button[@onclick='onLoginUser(this)']/preceding-sibling::div[@class='row error-login-panel']")).getText(), "Tài khoản không tồn tại!");
        driver.findElement(By.xpath("//div[@id='modal-login-v1']//button[@class='close']")).click();
        sleepInSecond(2);
        Assert.assertFalse(driver.findElement(By.xpath("//a[@onclick='onForgetPassword(this)']")).isDisplayed());
    }

    @Test
    public void TC_02_Fixed_Popup_InDOM_2() {
        driver.get("https://skills.kynaenglish.vn/");
        driver.findElement(By.xpath("//a[@class='login-btn']")).click();
        sleepInSecond(2);
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='k-popup-account-login']")).isDisplayed());
        driver.findElement(By.xpath("//input[@id='user-login']")).sendKeys("automation@gmail.com");
        driver.findElement(By.xpath("//input[@id='user-password']")).sendKeys("123456");
        driver.findElement(By.xpath("//button[@id='btn-submit-login']")).click();
        sleepInSecond(2);
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='password-form-login-message']")).getText(), "Sai tên đăng nhập hoặc mật khẩu");
        driver.findElement(By.xpath("//button[@class='k-popup-account-close close']")).click();
        sleepInSecond(2);
        Assert.assertFalse(driver.findElement(By.xpath("//div[@id='k-popup-account-login']")).isDisplayed());
    }

    @Test
    public void TC_03_Fixed_Popup_NotInDOM_1() {
        driver.get("https://tiki.vn/");
        driver.findElement(By.xpath("//img[@src='https://salt.tikicdn.com/ts/upload/07/d5/94/d7b6a3bd7d57d37ef6e437aa0de4821b.png']")).click();
        sleepInSecond(2);
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='ReactModal__Overlay ReactModal__Overlay--after-open']")).isDisplayed());
        driver.findElement(By.xpath("//p[@class='login-with-email']")).click();
        sleepInSecond(2);
        driver.findElement(By.xpath("//p[@class='forgot-pass']/preceding-sibling::form//button")).click();
        sleepInSecond(2);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='email']/parent::div/following-sibling::span[1]")).getText(), "Email không được để trống");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='email']/parent::div/following-sibling::span[2]")).getText(), "Mật khẩu không được để trống");
        driver.findElement(By.xpath("//img[@class='close-img']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertEquals(driver.findElements(By.xpath("//div[@class='ReactModal__Overlay ReactModal__Overlay--after-open']")).size(), 0);
    }

    @Test
    public void TC_04_Fixed_Popup_NotInDOM_1() {
        driver.get("https://www.facebook.com/");
        driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
        sleepInSecond(2);
        Assert.assertTrue(driver.findElement(By.xpath("//input[@name='firstname']//ancestor::div[@class='_8ien']")).isDisplayed());
        driver.findElement(By.xpath("//input[@name='firstname']//ancestor::div[@class='_8ien']/img")).click();
        sleepInSecond(2);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertEquals(driver.findElements(By.xpath("//input[@name='firstname']//ancestor::div[@class='_8ien']")).size(), 0);
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
