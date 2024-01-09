package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.xml.sax.Locator;

import java.time.Duration;
import java.util.Random;

public class Topic_23_JavascriptExecutor {
    WebDriver driver;
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_() {
        jsExecutor.executeScript("window.location = 'http://live.techpanda.org/'");
        sleepInSecond(3);
        Assert.assertEquals(jsExecutor.executeScript("return document.domain"),"live.techpanda.org");
        jsExecutor.executeScript("return document.url");
        WebElement mobileTab = driver.findElement(By.xpath("//a[text()='Mobile']"));
        jsExecutor.executeScript("arguments[0].click();", mobileTab);
        sleepInSecond(5);
        WebElement addToCartBtn = driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/following-sibling::div//span[text()='Add to Cart']"));
        jsExecutor.executeScript("arguments[0].click();", addToCartBtn);
        sleepInSecond(5);
        String text1 = (String) jsExecutor.executeScript("return document.documentElement.innerText;");
        Assert.assertTrue(text1.contains("Samsung Galaxy was added to your shopping cart."));
        WebElement customerService = driver.findElement(By.xpath("//a[text()='Customer Service']"));
        jsExecutor.executeScript("arguments[0].click();", customerService);
        sleepInSecond(5);
        Assert.assertEquals(jsExecutor.executeScript("return document.title"), "Customer Service");
        WebElement newLetter = driver.findElement(By.id("newsletter"));
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", newLetter);
        sleepInSecond(2);
        jsExecutor.executeScript("arguments[0].setAttribute('value', 'nv.vu28@gmail.com')", newLetter);
        sleepInSecond(3);
        WebElement submitBtn = driver.findElement(By.xpath("//span[text()='Subscribe']"));
        jsExecutor.executeScript("arguments[0].click();", submitBtn);
        sleepInSecond(5);
        String text2 = (String) jsExecutor.executeScript("return document.documentElement.innerText;");
        Assert.assertTrue(text2.contains("Thank you for your subscription."));
        jsExecutor.executeScript("window.location='https://www.facebook.com/'");
        sleepInSecond(5);
        Assert.assertEquals(jsExecutor.executeScript("return document.domain"), "facebook.com");
    }

    @Test
    public void TC_02_() {
        jsExecutor.executeScript("window.location='https://login.ubuntu.com/'");
        sleepInSecond(5);
        By cookiePop =  By.xpath("//dialog[@class='cookie-policy']");
        if (!driver.findElements(cookiePop).isEmpty() && driver.findElement(cookiePop).isDisplayed()) {
            WebElement popBtn = driver.findElement(By.xpath("//dialog[@class='cookie-policy']//button[@id='cookie-policy-button-accept']"));
            jsExecutor.executeScript("arguments[0].click();", popBtn);
        }
        WebElement email = driver.findElement(By.xpath("//div[@class='login-form']//input[@type='email']"));
        jsExecutor.executeScript("arguments[0].setAttribute('value', 'a')", email);
        sleepInSecond(3);
        WebElement loginBtn = driver.findElement(By.xpath("//button[@data-qa-id='login_button']"));
        jsExecutor.executeScript("arguments[0].click();", loginBtn);
        sleepInSecond(3);
        Assert.assertEquals(jsExecutor.executeScript("return arguments[0].validationMessage;", email), "Please enter an email address.");
        driver.switchTo().newWindow(WindowType.TAB).get("https://sieuthimaymocthietbi.com/account/register");
        sleepInSecond(3);
        WebElement loginBtn2 = driver.findElement(By.xpath("//button[text()='Đăng ký']"));
        jsExecutor.executeScript("arguments[0].click();", loginBtn2);
        sleepInSecond(3);
        WebElement lastNameInput = driver.findElement(By.xpath("//input[@id='lastName']"));
        Assert.assertEquals(jsExecutor.executeScript("return arguments[0].validationMessage;", lastNameInput), "Please fill out this field.");
    }

    @Test
    public void TC_03_() {
        jsExecutor.executeScript("window.location='http://live.techpanda.org/'");
        sleepInSecond(3);
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@id='header-account']//a[text()='My Account']")));
        sleepInSecond(3);
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@title='Create an Account']")));
        sleepInSecond(3);
        jsExecutor.executeScript("arguments[0].setAttribute('value', 'vu')", driver.findElement(By.id("firstname")));
        jsExecutor.executeScript("arguments[0].setAttribute('value', 'van')", driver.findElement(By.id("middlename")));
        jsExecutor.executeScript("arguments[0].setAttribute('value', 'nguyen')", driver.findElement(By.id("lastname")));
        String email = getEmailAddress();
        System.out.println(email);
        jsExecutor.executeScript("arguments[0].setAttribute('value', '"+getEmailAddress()+"')", driver.findElement(By.id("email_address")));
        jsExecutor.executeScript("arguments[0].setAttribute('value', '99999999')", driver.findElement(By.id("password")));
        jsExecutor.executeScript("arguments[0].setAttribute('value', '99999999')", driver.findElement(By.id("confirmation")));
        sleepInSecond(3);
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[@title='Register']")));
        sleepInSecond(5);
        String text3 = (String) jsExecutor.executeScript("return document.documentElement.innerText;");
        Assert.assertTrue(text3.contains("Thank you for registering with Main Website Store."));
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@title='Log Out']")));
        sleepInSecond(10);
        String page2title = (String) jsExecutor.executeScript("return document.title");
        Assert.assertEquals(page2title, "Home page");
    }

    @AfterClass
    public void afterClass() {
        // driver.quit();
    }

    public void sleepInSecond (long timeInSecond) {
        try {
            Thread.sleep(timeInSecond*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getEmailAddress() {
        Random randNum = new Random();
        return "vunguyen" + randNum.nextInt(99999) + "@gmail.com";
    }


}
