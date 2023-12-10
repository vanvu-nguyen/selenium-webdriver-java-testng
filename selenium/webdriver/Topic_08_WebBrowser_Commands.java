package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_08_WebBrowser_Commands {
    // Declaration for variable 'driver' with Selenium Interface 'WebDriver' that support the popular browser-drivers (Firefox, Chrome, Edge, Safari)
    WebDriver driver;

    // Declaration for TestNG annotation @BeforeClass which runs at the beginning of every testcase
    @BeforeClass
    public void beforeClass() {
        // Initialize browser-driver (Open new browser window)
        driver = new FirefoxDriver(); // **
        // Set timeout for returning result of function findElement() and findElements. Syntax version 3, 2, 1
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        // ... and syntax for version 4
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); // **
        driver.manage().window().maximize();
    }

    // Declaration for TestNG annotation @Test which runs as a function (testcase)
    @Test
    public void TC_01_() throws MalformedURLException {
        // Select get() method in driver field (relate to WebDriver) to access a webpage by passing url
        driver.get("https://www.facebook.com/"); // **
        // For closing the current tab on browser
        driver.close(); // *
        // For closing browser
        driver.quit(); // **
        /*
        Find the element with method findElement() of driver, then assign to a variable defined by Selenium interface WebElement
        Get the first element to implement on the next step if the result is more than 1 element.
        Return fail and stop the testcase when does not find any matched element
         */
        WebElement emailAddressTextBox = driver.findElement(By.id("email")); // **
        /*
        Find the elements with method findElements() of driver, then assign to a variable define by Selenium interface List<>
        Return the empty list when does not find any matched element and contuinue to implement the next step
         */
        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']")); // **
        // 2 methods findElement() and findElements() are influenced by function 'implicitWait'

        // This function used to get the current url address
        driver.getCurrentUrl(); // *
        // No need to declaration a variable if it's just used 1 time
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/");
        // This function used to get the html, css, js source code of the current page
        driver.getPageSource();
        // This function used to get the title of the current page
        driver.getTitle();
        // This function used to get the GUID of the current tab
        driver.getWindowHandle(); // *
        driver.getWindowHandles(); // *

        // The functions of manage():
        // Get cookies info (will learn more in Framework)
        driver.manage().getCookies();
        // Get log from Devtool:
        driver.manage().logs().get(LogType.DRIVER); // *
        // Set the time for waiting for searching element, ineffective if declare after findElement() function
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); // **
        // Set the time for waiting for loading a page
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        // Set the time for waiting for JavascriptExecutor framework finish execution
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(10));
        // Get time info of function: implicitlyWait(), pageLoadTimeout(), scriptTimeout()
        driver.manage().timeouts().getImplicitWaitTimeout();
        driver.manage().timeouts().getPageLoadTimeout();
        driver.manage().timeouts().getScriptTimeout();
        // Turn on fullscreen mode for browser (= press F11)
        driver.manage().window().fullscreen();
        // Maximize the browser window
        driver.manage().window().maximize();
        // Minimize the browser window to the taskbar/dock
        driver.manage().window().minimize(); // **
        // Set displayed dimension of browser window for test responsive
        driver.manage().window().setSize(new Dimension(1366,768));
        // Get displayed dimension of browser window
        driver.manage().window().getSize();
        // Set the position of the top-left browser border based on device display solution
        driver.manage().window().setPosition(new Point(0, 0));
        // Get the position of the browser window
        driver.manage().window().getPosition();
        // Go forward webpage
        driver.navigate().forward();
        // Refresh the webpage
        driver.navigate().refresh();
        // Go backwark the webpage
        driver.navigate().back();
        // Like function driver.get()
        driver.navigate().to("");
        driver.navigate().to(new URL(""));
        // Function to interact with alert()
        driver.switchTo().alert().getText(); // *
        driver.switchTo().alert().dismiss(); // *
        driver.switchTo().alert().accept(); // *
        driver.switchTo().alert().sendKeys(""); // *
        // Focus on the browser tab with given handle/name
        String homePageWindowId = driver.getWindowHandle();
        driver.switchTo().window(homePageWindowId); // *
        // Focus to the frame by given id/name/element/index
        driver.switchTo().frame(""); // *
        driver.switchTo().frame(0); // *
        driver.switchTo().frame(driver.findElement(By.id(""))); // *
        // Focus back to the previous frame
        driver.switchTo().defaultContent();
        // Focus to the parent frame
        driver.switchTo().parentFrame();
    }

    // Declaration for TestNG annotation @AfterClass which runs finally of every testcase
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
