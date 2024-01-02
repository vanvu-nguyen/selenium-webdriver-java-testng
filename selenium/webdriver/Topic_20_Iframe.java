package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_20_Iframe {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_() {
        driver.get("https://skills.kynaenglish.vn/");
        sleepInSecond(3);
        WebElement fanPageIframe = driver.findElement(By.xpath("//div[@class='face-content']/iframe"));
        Assert.assertTrue(fanPageIframe.isDisplayed());
        driver.switchTo().frame(fanPageIframe);
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='lfloat']")).getText().contains("followers"));
        driver.switchTo().defaultContent();
        WebElement chatIframe = driver.findElement(By.xpath("//div[@id='cs-live-chat']/iframe"));
        driver.switchTo().frame(chatIframe);
        driver.findElement(By.xpath("//div[@class='button_text']/following-sibling::div[contains(@class,'border_overlay')]")).click();
        driver.findElement(By.xpath("//input[@ng-model='login.username']")).sendKeys("John Wick");
        driver.findElement(By.xpath("//input[@ng-model='login.phone']")).sendKeys("0909444555");
        new Select(driver.findElement(By.xpath("//select[@id='serviceSelect']"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");
        driver.findElement(By.xpath("//textarea[@name='message']")).sendKeys("Java Bootcamp");
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//input[@id='live-search-bar']")).sendKeys("Excel");
        driver.findElement(By.xpath("//button[@class='search-button']")).click();
    }

    @Test
    public void TC_02_() {
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");
        sleepInSecond(5);
        driver.findElement(By.xpath("//div[@class='details__form-image']/img")).click();
        sleepInSecond(10);
        driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='details__form-template']/iframe")));
        new Select(driver.findElement(By.xpath("//label[contains(text(),'Year')]/following-sibling::select"))).selectByVisibleText("Senior");
        new Select(driver.findElement(By.xpath("//label[contains(text(),'Residence')]/following-sibling::select"))).selectByVisibleText("Off Campus");
        driver.findElement(By.xpath("//label[text()='Male']")).click();
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//nav[contains(@class,'header--desktop-floater')]//a[@title='Log in']")).click();
        sleepInSecond(20);
        driver.findElement(By.xpath("//button[@id='login']")).click();
        sleepInSecond(20);
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='message-error']")).getText(), "Username and password are both required.");
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
}
