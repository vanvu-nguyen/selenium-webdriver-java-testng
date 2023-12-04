package webdriver;

import com.beust.ah.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_06_XPathCSS_Practice {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_() {

        // Access url, then click Submit
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();

        // Check inline validation messages
        String fnInpErrMsg = driver.findElement(By.id("txtFirstname-error")).getText();
        Assert.assertEquals(fnInpErrMsg,"Vui lòng nhập họ tên");

        String emailInpErrMsg = driver.findElement(By.id("txtEmail-error")).getText();
        Assert.assertEquals(emailInpErrMsg, "Vui lòng nhập email");

        String cEmailInpErrMsg = driver.findElement(By.id("txtCEmail-error")).getText();
        Assert.assertEquals(cEmailInpErrMsg, "Vui lòng nhập lại địa chỉ email");

        String pwInpErrMsg = driver.findElement(By.id("txtPassword-error")).getText();
        Assert.assertEquals(pwInpErrMsg, "Vui lòng nhập mật khẩu");

        String cPwInpErrMsg = driver.findElement(By.id("txtCPassword-error")).getText();
        Assert.assertEquals(cPwInpErrMsg, "Vui lòng nhập lại mật khẩu");

        String telInpErrMsg = driver.findElement(By.id("txtPhone-error")).getText();
        Assert.assertEquals(telInpErrMsg, "Vui lòng nhập số điện thoại.");
    }

    @Test
    public void TC_02_() {

        // Access url
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        // Input valid values, except email and confirm-email, then click Submit
        driver.findElement(By.id("txtFirstname")).sendKeys("vu nguyen");
        driver.findElement(By.id("txtEmail")).sendKeys("123@123@1");
        driver.findElement(By.id("txtCEmail")).sendKeys("123@123@1");
        driver.findElement(By.id("txtPassword")).sendKeys("123456789");
        driver.findElement(By.id("txtCPassword")).sendKeys("123456789");
        driver.findElement(By.id("txtPhone")).sendKeys("0773435451");

        driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();

        // Check email and confirm-email inline validation messages
        String emailInpErrMsg = driver.findElement(By.id("txtEmail-error")).getText();
        Assert.assertEquals(emailInpErrMsg, "Vui lòng nhập email hợp lệ");

        String cEmailInpErrMsg = driver.findElement(By.id("txtCEmail-error")).getText();
        Assert.assertEquals(cEmailInpErrMsg, "Vui lòng nhập email hợp lệ");
    }
        // TC fail because design (document provided) is not match --> bug

    @Test
    public void TC_03_() {

        // Access url
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        // Input valid values, but confirm-password is not matching, then click Submit
        driver.findElement(By.id("txtFirstname")).sendKeys("vu nguyen");
        driver.findElement(By.id("txtEmail")).sendKeys("nvv@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("nvv@gmail.net");
        driver.findElement(By.id("txtPassword")).sendKeys("123456789");
        driver.findElement(By.id("txtCPassword")).sendKeys("123456789");
        driver.findElement(By.id("txtPhone")).sendKeys("0773435451");

        driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();

        // Check confirm-email inline validation message
        String cEmailInpErrMsg = driver.findElement(By.id("txtCEmail-error")).getText();
        Assert.assertEquals(cEmailInpErrMsg, "Email nhập lại không đúng");
    }

    @Test
    public void TC_04_() {

        // Access url
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        // Input valid value, except password and confirm-password, then click Submit
        driver.findElement(By.id("txtFirstname")).sendKeys("vu nguyen");
        driver.findElement(By.id("txtEmail")).sendKeys("nv.vu29@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("nv.vu29@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("123");
        driver.findElement(By.id("txtCPassword")).sendKeys("123");
        driver.findElement(By.id("txtPhone")).sendKeys("0773435453");

        driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();

        // Check password and confirm-password inline validation messages
        String pwInpErrMsg = driver.findElement(By.id("txtPassword-error")).getText();
        Assert.assertEquals(pwInpErrMsg, "Mật khẩu phải có ít nhất 6 ký tự");

        String cPwInpErrMsg = driver.findElement(By.id("txtCPassword-error")).getText();
        Assert.assertEquals(cPwInpErrMsg, "Mật khẩu phải có ít nhất 6 ký tự");
    }

    @Test
    public void TC_05_() {

        // Access url
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        // Input valid values, but confirm-password is not matching, then click Submit
        driver.findElement(By.id("txtFirstname")).sendKeys("vu nguyen");
        driver.findElement(By.id("txtEmail")).sendKeys("nv.vu29@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("nv.vu29@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("123456");
        driver.findElement(By.id("txtCPassword")).sendKeys("123457");
        driver.findElement(By.id("txtPhone")).sendKeys("0773435453");

        driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();

        // Check password and confirm-password inline validation messages
        String cPwInpErrMsg = driver.findElement(By.id("txtCPassword-error")).getText();
        Assert.assertEquals(cPwInpErrMsg, "Mật khẩu bạn nhập không khớp");
    }

    @Test
    public void TC_06_() {

        // Access url
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        // Input valid values but phone number is missed character, then click Submit
        driver.findElement(By.id("txtFirstname")).sendKeys("vu nguyen");
        driver.findElement(By.id("txtEmail")).sendKeys("nv.vu29@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("nv.vu29@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("123456");
        driver.findElement(By.id("txtCPassword")).sendKeys("123456");
        driver.findElement(By.id("txtPhone")).sendKeys("077343545");

        driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();

        // Check phone number inline validation message
        String telInpErrMsg = driver.findElement(By.id("txtPhone-error")).getText();
        Assert.assertEquals(telInpErrMsg, "Số điện thoại phải từ 10-11 số.");

        // Clear phone number value
        driver.findElement(By.id("txtPhone")).clear();

        // Re-fill the phone number which is not started with "0" but enough character, then click Submit
        driver.findElement(By.id("txtPhone")).sendKeys("7734354512");
        driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();

        // Re-declaration for the new message
        String telInpErrMsg2 = driver.findElement(By.id("txtPhone-error")).getText();

        // Check phone number inline validation message
        Assert.assertEquals(telInpErrMsg2, "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019");
    }
    // TC fail because design (document provided) is not match --> bug

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
