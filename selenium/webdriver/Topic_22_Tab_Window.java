package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
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
    WebDriver driver1;

    Actions actions;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver1 = new FirefoxDriver();
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

    @Test
    public void TC_02_() {
        driver.get("https://skills.kynaenglish.vn/");
        // No popup shown -> could not catch the element
        driver.findElement(By.xpath("//div[@class='hotline']//a[@href='https://www.facebook.com/kyna.vn']/img")).click();
        sleepInSecond(3);
        switchToByTitle("Facebook");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/kyna.vn");
        switchToByTitle("Kyna.vn - Học online cùng chuyên gia");
        driver.findElement(By.xpath("//div[@class='hotline']//a[@href='https://www.youtube.com/user/kynavn']/img")).click();
        sleepInSecond(3);
        switchToByTitle("Kyna.vn - YouTube");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.youtube.com/user/kynavn");
        switchToByTitle("Kyna.vn - Học online cùng chuyên gia");
        Set<String> allExistedTabIds = driver.getWindowHandles();
        for (String eId : allExistedTabIds) {
            driver.switchTo().window(eId);
            if (!driver.getTitle().equals("Kyna.vn - Học online cùng chuyên gia")) {
                driver.close();
            }
        }
    }

    @Test
    public void TC_03_() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//a[@href='http://live.techpanda.org/index.php/mobile.html']")).click();
        sleepInSecond(3);
        driver.findElement(By.xpath("//a[@title='Xperia']/following-sibling::div[@class='product-info']//a[text()='Add to Compare']")).click();
        sleepInSecond(3);
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']")).getText(), "The product Sony Xperia has been added to comparison list.");
        driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/following-sibling::div[@class='product-info']//a[text()='Add to Compare']")).click();
        sleepInSecond(3);
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']")).getText(), "The product Samsung Galaxy has been added to comparison list.");
        driver.findElement(By.xpath("//button[@title='Compare']")).click();
        sleepInSecond(3);
        switchToByTitle("Products Comparison List - Magento Commerce");
        Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
        driver.findElement(By.xpath("//button[@title='Close Window']")).click();
        switchToByTitle("Mobile");
        driver.findElement(By.xpath("//a[text()='Clear All']")).click();
        driver.switchTo().alert().accept();
        sleepInSecond(3);
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The comparison list was cleared.");
    }

    @Test
    public void TC_04_() {
        // Using function newWindow(WindowType.TAB/WINDOW), help to open new tab/window and the available driver will switch to the demand tab/window
        driver.get("https://www.facebook.com/home.php");
        driver.switchTo().newWindow(WindowType.TAB).get("https://open.spotify.com/");
        // If you want to switch to any other tab/window, you have to use the function switchToByTitle()
    }

    @Test
    public void TC1() {
        driver.get("https://www.youtube.com/");
        driver1.get("https://open.spotify.com/");
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