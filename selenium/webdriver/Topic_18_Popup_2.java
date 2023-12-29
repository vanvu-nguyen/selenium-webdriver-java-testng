package webdriver;

import com.beust.ah.A;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Interaction;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.security.Key;
import java.time.Duration;

public class Topic_18_Popup_2 {
    WebDriver driver;
    Actions actions;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        actions = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        // driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Random_Popup_Not_InDOM() {
        driver.get("https://www.javacodegeeks.com/");
        sleepInSecond(30);
        By newLetterPopUpXpath = By.xpath("//div[contains(@class,'lepopup-popup-container')]/div[not(starts-with(@style,'display:none'))]");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        if (!driver.findElements(newLetterPopUpXpath).isEmpty() && driver.findElements(newLetterPopUpXpath).get(0).isDisplayed()) {
            driver.findElement(By.xpath("//a[text()='×']")).click();
            System.out.println("Popup đã hiển thị!");
        } else {
            System.out.println("Popup đã không hiển thị!");
        }
        driver.findElement(By.id("search-input")).sendKeys("Agile Testing Explained");
        actions.keyDown(Keys.ENTER).release().perform();
        sleepInSecond(10);
        Assert.assertEquals(driver.findElement(By.xpath("//ul[@id='posts-container']/li[1]//h2[@class='post-title']/a")).getText(), "Agile Testing Explained");
    }

    @Test
    public void TC_02_Random_Popup_InDOM() {
        driver.get("https://vnk.edu.vn/");
        sleepInSecond(30);
        By courseInfo = By.xpath("//div[@data-tl-type='lightbox']//a[@href='https://vnk.edu.vn/lich-khai-giang/']");
        if (!driver.findElements(courseInfo).isEmpty() && driver.findElements(courseInfo).get(0).isDisplayed()) {
            driver.findElement(By.xpath("//div[@data-tl-type='lightbox']//*[name()='svg' and @data-name='closeclose']")).click();
            System.out.println("Popup đã hiển thị!");
        } else {
            System.out.println("Popup đã không hiển thị!");
        }
    }

    @Test
    public void TC_02_Random_Popup_InDOM_Display_Anytime() {
        driver.get("https://vnk.edu.vn/");
        sleepInSecond(30);
        vuFindElement(By.xpath("//ul[@id='menu-main-menu']//a[@href='https://vnk.edu.vn/lien-he/']")).click();
        }

    @Test
    public void TC_03_Random_Popup_Not_InDOM() {
        driver.get("https://dehieu.vn/");
        sleepInSecond(10);
        By phonePopUp = By.xpath("//input[@id='popup-phone']");
        if (!driver.findElements(phonePopUp).isEmpty() && driver.findElement(phonePopUp).isDisplayed()) {
            if (driver.manage().window().getSize().getWidth() < 1920) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[@id='close-popup']")));
            } else {
                driver.findElement(By.xpath("//button[@id='close-popup']")).click();
            }
        }
        sleepInSecond(2);
        driver.findElement(By.xpath("//a[@href='https://dehieu.vn/dang-ky']")).click();
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

    public WebElement vuFindElement(By locator) {
        By courseInfo = By.xpath("//div[@data-tl-type='lightbox']//a[@href='https://vnk.edu.vn/lich-khai-giang/']");
        if (!driver.findElements(courseInfo).isEmpty() && driver.findElements(courseInfo).get(0).isDisplayed()) {
            driver.findElement(By.xpath("//div[@data-tl-type='lightbox']//*[name()='svg' and @data-name='closeclose']")).click(); // before finding element, this function will check if the popup is displayed, if the popup displayed, the function will close the popup first
            System.out.println("Popup đã hiển thị!");
        }
        return driver.findElement(locator); // ...then find target element
    }
        // Only call the function on the page where the popup display. Return to use "driver.findElement" when run to other page.
}
