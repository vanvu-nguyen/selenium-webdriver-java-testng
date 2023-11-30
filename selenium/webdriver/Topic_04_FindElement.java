package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_04_FindElement {
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
        driver.get("http://live.techpanda.org/");
        /*
        This XPath does not give the unique result so the function will run for the first one its detect
        , but in this case the first "My Account" hidden in side a dropdown
        , and we don't have any previous action to view the dropdown so selenium unable to click the first "My Account"
        -> TC fail
        */
        driver.findElement(By.xpath("//a[@title='My Account']")).click();
    }

    @Test
    public void TC_02_() {
        driver.get("http://live.techpanda.org/");
        /*
        Use the syntax //parent_tag_name[@attribute='value']//child_tag_name[@attribute='value']
        to determine the unique element (the second "My Account")
        -> TC success
        */
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
    }

    @AfterClass
    public void afterClass() {
        // driver.quit();
    }
}

        // The XPath function starts-with() is good for using with the element attribute has a prefix
        // Java getText() function will get all text content inside the html tag (all text content in parent node and child node)


