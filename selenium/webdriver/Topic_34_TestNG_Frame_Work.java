package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_34_TestNG_Frame_Work {
    WebDriver driver;
    WebDriverWait explicitWait;
    By enterEmailLocator = By.xpath("//input[@name='reg_email__']");
    By reEnterEmailLocator = By.xpath("//input[@name='reg_email_confirmation__']");

    By closePop = By.xpath("//input[@name='reg_email__']/ancestor::div[@class='_8ien']/img");


    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test (groups = {"login", "signup"})
    public void TC_01_Visibility() {
        driver.get("https://www.facebook.com/");
        driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
        sleepInSecond(5);
        driver.findElement(enterEmailLocator).sendKeys("nv.vu28@gmail.com");
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(reEnterEmailLocator));
        Assert.assertTrue(driver.findElement(reEnterEmailLocator).isDisplayed());
    }

    @Test (alwaysRun = true)
    public void TC_02_Invisibility_In_DOM() {
        driver.get("https://www.facebook.com/");
        driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
        sleepInSecond(5);
        driver.findElement(enterEmailLocator).sendKeys("nv.vu28@gmail.com");
        driver.findElement(enterEmailLocator).clear();
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(reEnterEmailLocator));
        Assert.assertFalse(driver.findElement(reEnterEmailLocator).isDisplayed());
    }

    @Test (priority = 1, enabled = false, description = "testCaseId000001")
    public void TC_03_Invisibility_Not_In_DOM() {
        driver.get("https://www.facebook.com/");
        driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
        sleepInSecond(5);
        driver.findElement(enterEmailLocator).sendKeys("nv.vu28@gmail.com");
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(reEnterEmailLocator));
        Assert.assertTrue(driver.findElement(reEnterEmailLocator).isDisplayed());
        driver.findElement(closePop).click();
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(reEnterEmailLocator));
        Assert.assertTrue(driver.findElements(reEnterEmailLocator).isEmpty());
    }

    @Test (dataProvider = "user_pass")
    public void TC_04_MailChimp_Register(String username, String password) throws InterruptedException {
        driver.get("https://login.mailchimp.com/signup/");
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(username);
        driver.findElement(By.id("new_password")).clear();
        driver.findElement(By.id("new_password")).sendKeys(password);
    }

    @DataProvider(name = "user_pass")
    public Object[][] UserAndPasswordData() {
        return new Object[][] {
                {"vanvunguyen150491@gmail.com", "111111111"},
                {"vanvunguyen150492@gmail.com", "222222222"},
                {"vanvunguyen150493@gmail.com", "333333333"}
        };
    }

    @Test
    public void TC_04_Presence() {
        driver.get("https://www.facebook.com/");
        driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
        sleepInSecond(5);
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(reEnterEmailLocator));
        Assert.assertFalse(driver.findElement(reEnterEmailLocator).isDisplayed());
    }

    @Test
    public void TC_05_Staleness() {
        // Wait for an element disapear, this element used to be displayed, but no longer displayed.
        driver.get("https://www.facebook.com/");
        driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
        sleepInSecond(5);
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(reEnterEmailLocator));
        // reEnterElement is already displayed, we declare for it
        WebElement reEnterElement = driver.findElement(reEnterEmailLocator);
        driver.findElement(closePop).click();
        // reEnterElement is disapear in DOM, but we declare for it before (script above)
        explicitWait.until(ExpectedConditions.stalenessOf(reEnterElement));
        Assert.assertTrue(driver.findElements(reEnterEmailLocator).isEmpty());
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
