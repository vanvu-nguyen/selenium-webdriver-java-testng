package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.Date;

public class Topic_30_Wait_Practice {
    WebDriver driver;
    WebDriverWait explicitWait;
    String projectPath = System.getProperty("user.dir");


    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Invisibility() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.xpath("//div[@id='start']/button")).click();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(3));
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
    }
    @Test
    public void TC_02_Invisibility() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.xpath("//div[@id='start']/button")).click();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
    }
    @Test
    public void TC_03_Invisibility() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.xpath("//div[@id='start']/button")).click();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
    }

    @Test
    public void TC_04_Visibility() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.xpath("//div[@id='start']/button")).click();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(3));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='finish']/h4")));
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
    }
    @Test
    public void TC_05_Visibility() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.xpath("//div[@id='start']/button")).click();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='finish']/h4")));
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
    }
    @Test
    public void TC_06_Visibility() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.xpath("//div[@id='start']/button")).click();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='finish']/h4")));
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
    }

    @Test
    public void TC_07_() {
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//legend[text()='Selected Dates:']/following-sibling::div/span")));
        Assert.assertEquals(driver.findElement(By.xpath("//legend[text()='Selected Dates:']/following-sibling::div/span")).getText(), "No Selected Dates to display.");
        driver.findElement(By.xpath("//div[@class='calendarContainer']//a[text()='9']")).click();
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@style,'absolute')]/div[@class='raDiv']")));
        explicitWait.until(ExpectedConditions.attributeContains(By.xpath("//div[@class='calendarContainer']//a[text()='9']/parent::td"), "class", "rcSelected"));
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='datesContainer']//span")).getText().contains("9"));
    }

    @Test
    public void TC_08_() {
        driver.get("https://gofile.io/welcome");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("spinner-border"))));
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/uploadFiles']/button"))).click();
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("spinner-border"))));
        String hanoiFile = "hanoi.jpeg";
        String saigonFile = "saigon.jpeg";
        String danangFile = "danang.jpeg";
        String hanoiPath = projectPath + File.separator + "uploadFiles" + File.separator + hanoiFile;
        String saigonPath = projectPath + File.separator + "uploadFiles" + File.separator + saigonFile;
        String danangPath = projectPath + File.separator + "uploadFiles" + File.separator + danangFile;
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='filesUpload']//button[contains(@class,'filesUploadButton')]")));
        driver.findElement(By.xpath("//div[@id='filesUpload']//button[contains(@class,'filesUploadButton')]/following-sibling::input[@id='filesUploadInput']")).sendKeys(hanoiPath + "\n" + saigonPath + "\n" + danangPath);
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.className("spinner-border")))));
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='mainUpload']//div[contains(@class,'border-success')]"))).isDisplayed());
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'mainUploadSuccessLink')]//a"))).click();
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("spinner-border"))));

        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='" + hanoiFile + "']/ancestor::div)[last()]/following-sibling::div[3]//button/span[text()='Download']"))).isDisplayed());
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='" + danangFile + "']/ancestor::div)[last()]/following-sibling::div[3]//button/span[text()='Download']"))).isDisplayed());
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='" + saigonFile + "']/ancestor::div)[last()]/following-sibling::div[3]//button/span[text()='Download']"))).isDisplayed());

        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='" + hanoiFile + "']/ancestor::div)[last()]/following-sibling::div[3]//button/span[text()='Play']"))).isDisplayed());
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='" + danangFile + "']/ancestor::div)[last()]/following-sibling::div[3]//button/span[text()='Play']"))).isDisplayed());
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='" + saigonFile + "']/ancestor::div)[last()]/following-sibling::div[3]//button/span[text()='Play']"))).isDisplayed());
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

