package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Locale;

public class Topic_13_Button {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_() {
        driver.get("https://egov.danang.gov.vn/reg");
        WebElement registerButton = driver.findElement(By.xpath("//input[@class='egov-button']"));
        Assert.assertFalse(registerButton.isEnabled());
        driver.findElement(By.xpath("//input[@name='chinhSach']")).click();
        sleepInSecond(2);
        Assert.assertTrue(registerButton.isEnabled());
        Color buttonRgbColor = Color.fromString(registerButton.getCssValue("background-color"));
        System.out.println(registerButton.getCssValue("background-color"));
        String buttonHexColor = buttonRgbColor.asHex();
        System.out.println(buttonHexColor);
        Assert.assertEquals(buttonHexColor.toLowerCase(),"#ef5a00");
    }

    @Test
    public void TC_02_() {
        driver.get("https://www.fahasa.com/customer/account/create");
        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
        sleepInSecond(2);
        WebElement loginButton = driver.findElement(By.xpath("//button[@class='fhs-btn-login']"));
        Assert.assertFalse(loginButton.isEnabled());
        String loginButtonColor = loginButton.getCssValue("background-color");
        System.out.println(loginButtonColor);
        loginButtonColor = Color.fromString(loginButtonColor).asHex().toLowerCase();
        Assert.assertEquals(loginButtonColor, "#000000");
        driver.findElement(By.id("login_username")).clear();
        driver.findElement(By.id("login_username")).sendKeys("nv.vu28@gmail.com");
        driver.findElement(By.id("login_password")).clear();
        driver.findElement(By.id("login_password")).sendKeys("123456789");
        sleepInSecond(2);
        Assert.assertTrue(loginButton.isEnabled());
        loginButtonColor = Color.fromString(loginButton.getCssValue("background-color")).asHex().toLowerCase();
        System.out.println(loginButtonColor);
        Assert.assertEquals(loginButtonColor, "#c92127");
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
