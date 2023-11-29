package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
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
                .below(inputPasswordElement)
                .near(forgotPasswordElement));

        // Print out element tag content
        System.out.println(rememberMeTextElement.getText());

        // Print number of <a> tag in this page
        List<WebElement> allLinks = driver.findElements(RelativeLocator.with(By.cssSelector("a")));
        System.out.println(allLinks.size());
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

        // XPath format: //tag_name[@attribute_name='attribute_value']

        /*
        Locator priority (the best locator must pass the order 1->2->3)
        1. Unique: attribute value must be unique (check XPath on Devtool to determine the value is unique or not, do not use the attribute if Devtool return more than 1 result)
        2. Meaning: attribute value must has meaning with the tag using
        3. Prioritize id/class/name attribute
        ---
        4. If element does not have id/class/name, other attributes is the same, we can use any of them, use any attribute unique and has meaning
        5. Do not use the attribute does not have value (null)
        ---
        6. With link (<a> tag), do not use the href attribute because maybe the domain of link will be changed or the link is wrong with differnce envoriment (a framework need to support all env/server) -> use content of the <a> tag
        7. With link (<a> tag), do not use the content of <a> tag when page has multi language -> use href attribute
        ---
        8. Do not use //* because it make performance slowly
        9. Do not place the value in "", it better '' -> this makes code more beautiful
         */
