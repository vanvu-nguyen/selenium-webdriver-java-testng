package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

public class Topic_24_Upload_Files {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Single_Upload() {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        sleepInSecond(3);
        String hanoiFile = "hanoi.jpeg";
        String saigonFile = "saigon.jpeg";
        String danangFile = "danang.jpeg";
        String hanoiPath = projectPath + File.separator + "uploadFiles" + File.separator + hanoiFile;
        String saigonPath = projectPath + File.separator + "uploadFiles" + File.separator + saigonFile;
        String danangPath = projectPath + File.separator + "uploadFiles" + File.separator + danangFile;
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(hanoiPath);
        sleepInSecond(5);
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(saigonPath);
        sleepInSecond(5);
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(danangPath);
        sleepInSecond(5);
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + hanoiFile + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + saigonFile + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + danangFile + "']")).isDisplayed());
        for (int i = 0; i < driver.findElements(By.xpath("//tr//button[@class='btn btn-primary start']")).size(); i++) {
            driver.findElements(By.xpath("//tr//button[@class='btn btn-primary start']")).get(i).click();
        }
        sleepInSecond(5);
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[text()='" + hanoiFile + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[text()='" + saigonFile + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[text()='" + danangFile + "']")).isDisplayed());
    }

    @Test
    public void TC_02_Multiple_Upload() {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        sleepInSecond(3);
        String hanoiFile = "hanoi.jpeg";
        String saigonFile = "saigon.jpeg";
        String danangFile = "danang.jpeg";
        String hanoiPath = projectPath + File.separator + "uploadFiles" + File.separator + hanoiFile;
        String saigonPath = projectPath + File.separator + "uploadFiles" + File.separator + saigonFile;
        String danangPath = projectPath + File.separator + "uploadFiles" + File.separator + danangFile;
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(hanoiPath + "\n" + saigonPath + "\n" + danangPath);
        sleepInSecond(5);
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + hanoiFile + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + saigonFile + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + danangFile + "']")).isDisplayed());
        for (int i = 0; i < driver.findElements(By.xpath("//tr//button[@class='btn btn-primary start']")).size(); i++) {
            driver.findElements(By.xpath("//tr//button[@class='btn btn-primary start']")).get(i).click();
        }
        sleepInSecond(5);
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[text()='" + hanoiFile + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[text()='" + saigonFile + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[text()='" + danangFile + "']")).isDisplayed());
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
