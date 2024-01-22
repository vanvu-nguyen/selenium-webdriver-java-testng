package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_31_Mixing_Implicit_Explicit {
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_() {

    }

    @Test
    public void TC_02_() {
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

// Nếu có set tgian cho cả im/explicitWait -> sẽ tìm element trong tg implicit trước cho đến khi tìm ra element hoặc hết thời gian
// -> sau đó trở về chờ element theo tg của explicit, lúc đó nếu:
// + chưa hết/vừa đủ thời gian mà thoả mãn điều kiện -> pass
// + đã quá thời gian mà chưa thoả mãn -> fail
// + đã quá thời gian và thoả mãn điều kiện -> ?

// Chưa demo lại các case
