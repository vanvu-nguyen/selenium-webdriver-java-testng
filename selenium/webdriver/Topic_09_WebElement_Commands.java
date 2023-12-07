package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_09_WebElement_Commands {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Element() {
        // Only find element example
        driver.findElement(By.id(""));
        // Find element then interact example
        driver.findElement(By.id("submit")).click();
        // Find element and initialize variable for the element, then interact to variable
        WebElement fullNameTextBox = driver.findElement(By.id("fullName"));
        fullNameTextBox.clear();
        fullNameTextBox.sendKeys("Automation FC");
        fullNameTextBox.getAttribute("type");

        // The function:
        // For erasing input data of an editable input field, used before sendKey()
        driver.findElement(By.id("")).clear(); // **
        // For inputting value for an editable field
        driver.findElement(By.id("")).sendKeys(""); // **
        // For clicking on an element
        driver.findElement(By.id("")).click(); // **
        // For finding element from element
        driver.findElement(By.id("")).findElement(By.id(""));
        // For finding element then return a element
        WebElement textBox = driver.findElement(By.id(""));
        // For finding elements then return a list (Generic definition)
        List<WebElement> textBoxes = driver.findElements(By.id(""));
        // For verifying checkbox/radio/default dropdown is selected or not (have the privated library for dropdown called Select)
        Assert.assertTrue(driver.findElement(By.id("")).isSelected()); // *
            // Extend: Custom dropdown
            Select select = new Select(driver.findElement(By.id("")));
        // For verifying element is displayed or not
        Assert.assertTrue(driver.findElement(By.id("")).isDisplayed()); // **
        // For verifying an element is allowed to interact or not (is read-only mode or not)
        Assert.assertTrue(driver.findElement(By.id("")).isEnabled()); // *
        // Get the attribute's value of an element
        driver.findElement(By.id("")).getAttribute(""); // *
        // Get Accessibility info (on Devtool tab Accessibility)
        driver.findElement(By.id("")).getAccessibleName(); // *
        // Get DOM attribute's value of an element
        driver.findElement(By.id("")).getDomAttribute(""); // *
        // Get the attribute's value even if the element value has been modified after page has been loaded
        driver.findElement(By.id("")).getDomProperty(""); // *
        // Get the css value of an element
        driver.findElement(By.id("")).getCssValue("background-color"); // *
        // Get the location of element
        Point elementLocation = driver.findElement(By.id("")).getLocation();
        elementLocation.getX();
        elementLocation.getY();
        // Get width and height of an element
        Dimension addressSize = driver.findElement(By.id("")).getSize();
        // Get the location and dimension of element
        Rectangle nameTextBoxRect = driver.findElement(By.id("")).getRect();
        Point namePoint = nameTextBoxRect.getPoint();
        Dimension nameSize = nameTextBoxRect.getDimension();
        nameSize.getHeight();
        nameSize.getWidth();
        // ... will learn more at lesson JavascriptExecutor
        driver.findElement(By.id("")).getShadowRoot();
        // Get html tag name of a element. For xpath, only apply for //*
        driver.findElement(By.id("")).getTagName();
        // Get visible text content of an element
        driver.findElement(By.id("")).getText(); // **
        // Screen shot and store to a specified location
        driver.findElement(By.id("")).getScreenshotAs(OutputType.BASE64); // *
        // Submit the input data in a form tag or any tag inside form tag (behavier like press Enter after input data to a textbox)
        driver.findElement(By.id("")).submit();

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
