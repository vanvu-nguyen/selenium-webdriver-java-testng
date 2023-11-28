package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_03_Relative_Locator {
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
        driver.get("https://demo.nopcommerce.com/login?returnUrl=%2Fregister");

        // Login button declaration
        By loginButtonBy = By.cssSelector("button.login-button");
        WebElement loginButtonElement = driver.findElement(By.cssSelector("button.login-button"));

        // Remember me checkbox declaration
        By rememberMeCheckboxBy = By.cssSelector("#RememberMe");
        WebElement rememberMeCheckboxElement = driver.findElement(By.cssSelector("#RememberMe"));

        // Forgot password declaration
        By forgotPasswordBy = By.cssSelector("span.forgot-password");
        WebElement forgotPasswordElement = driver.findElement(By.cssSelector("span.forgot-password"));

        // Input password declaration
        By inputPasswordBy = By.cssSelector("input.password");
        WebElement inputPasswordElement = driver.findElement(By.cssSelector("input.password"));

        // Find label of checkbox based on available variable with relative locator
        WebElement rememberMeTextElement = driver
                .findElement(RelativeLocator.with(By.tagName("label"))
                .above(loginButtonElement)
                .toRightOf(rememberMeCheckboxElement)
                .toLeftOf(forgotPasswordElement)
                .below(inputPasswordElement));

        // Print out element tag content
        System.out.println(rememberMeTextElement.getText());
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
