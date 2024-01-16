package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.xml.sax.Locator;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class Topic_29_Explicit_Wait {
    WebDriver driver;
    // Initialize variable for WebDriverWait
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        // Declaration for variable for driver with a duration of 10 seconds, default polling 500ms
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void TC_01_() {
        // Wait until an alert present in DOM then initialize and declaration for the return data type (Alert)
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        // Wait until an element disappear from DOM
        explicitWait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath("//p"))));
        // Wait until an element appear in DOM by passing locator argument
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p")));
        // Wait until at least one element appear in DOM by passing one locator, will not use entire explicit Duration
        driver.get("https://vnexpress.net/");
        List<WebElement> listE = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//p")));
        System.out.println(listE.size());
        // Wait until a nested element appear in DOM by passing a parent element/locator and a child locator
        explicitWait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(driver.findElement(By.id("parentParagraph")), By.id("childParagraph")));
        explicitWait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(By.id("parentParagraph"), By.id("childParagraph")));
        // Wait until an element appear in DOM and visible by passing an element/locator
        explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("sampleId"))));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sampleId")));
        // Wait until ALL elements appear in DOM and visible by passing a list/many elements/a single element
        explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.id("1")), driver.findElement(By.id("2"))));
        explicitWait.until(ExpectedConditions.visibilityOfAllElements(listE));
        explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.id("2"))));
        // Wait until an element visible and enable by passing a locator/element
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.id("sampleID")));
        explicitWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("sampleId"))));
        // Wait until the current page title match with the given string
        explicitWait.until(ExpectedConditions.titleIs("Expected Title"));
        Assert.assertEquals(driver.getTitle(), "Expected Title");
        // Wait until all conditions pass
        explicitWait.until(ExpectedConditions.and(ExpectedConditions.visibilityOf(driver.findElement(By.id("sampleId"))), ExpectedConditions.attributeContains(By.xpath("//input"), "value", "sampleValue")));
        // Wait until one of condition pass
        explicitWait.until(ExpectedConditions.or(ExpectedConditions.visibilityOf(driver.findElement(By.id("sampleId"))), ExpectedConditions.attributeContains(By.xpath("//input"), "value", "sampleValue")));
        // Wait until an element contains the expected value of a specific attribute
        explicitWait.until(ExpectedConditions.attributeContains(By.id("sampleId"), "value", "sampleValue"));
        explicitWait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("sampleId")), "value", "sampleValue"));
        // Wait until an element has an exact expected value of a specific attribute
        explicitWait.until(ExpectedConditions.attributeToBe(By.id("sampleId"), "value", "sampleValue"));
        explicitWait.until(ExpectedConditions.attributeToBe(driver.findElement(By.id("sampleId")), "value", "sampleValue"));
        // Wait until an element has an attribute not null
        explicitWait.until(ExpectedConditions.attributeToBeNotEmpty(driver.findElement(By.id("sampleId")), "value"));
        // Wait until an element has an exact expected value of a specific DOM attribute (HTML attribute)
        explicitWait.until(ExpectedConditions.domAttributeToBe(driver.findElement(By.id("sampleID")), "value", "sampleValue" ));
        // Wait until an element has an exact expected value of a specific DOM property (DOM property)
        explicitWait.until(ExpectedConditions.domPropertyToBe(driver.findElement(By.id("sampleID")), "value", "sampleValue" ));
        // Wait until an element successfully be selected (checkbox/radio/select option) by passing locator/element
        explicitWait.until(ExpectedConditions.elementToBeSelected(By.id("sampleID")));
        explicitWait.until(ExpectedConditions.elementToBeSelected(driver.findElement(By.id("sampleID"))));
        // Wait until an element successfully be selected or not by passing locator/element
        explicitWait.until(ExpectedConditions.elementSelectionStateToBe(By.id("sampleId"), false));
        explicitWait.until(ExpectedConditions.elementSelectionStateToBe(driver.findElement(By.id("sampleId")), true));
        // Wait until a frame available, if the frame available switch to it by passing id/name, index, locator, element
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.getWindowHandle()));
        // Wait until an element invisible or not present on the DOM
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("sampleId")));
        // Wait until an element invisible anymore
        explicitWait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.id("sampleID"))));
        // Wait until ALL given elements invisible anymore by passing a list of/many elements
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(listE));
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElement(By.id("1")), driver.findElement(By.id("2"))));
        // Wait until an element with given text invisible or not present on the DOM
        explicitWait.until(ExpectedConditions.invisibilityOfElementWithText(By.id("sampleId"), "header"));
        // Wait until return a string value from a javascript code execute
        explicitWait.until(ExpectedConditions.jsReturnsValue("document.documentElement.innerText;"));
        // Wait until a javascript execute then throw no exception
        explicitWait.until(ExpectedConditions.javaScriptThrowsNoExceptions("document.documentElement.innerText;"));
        // Wait until a specific number of element present on the DOM
        explicitWait.until(ExpectedConditions.numberOfElementsToBe(By.id("sampleId"), 5));
        explicitWait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.id("sampleID"), 5));
        explicitWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.id("sampleID"), 5));
        // Wait until a specific number of window/tab open
        explicitWait.until(ExpectedConditions.numberOfWindowsToBe(5));
        // Wait until a element has a specific text
        explicitWait.until(ExpectedConditions.textToBe(By.id("sampleId"), "Expected Text"));
        // ???
        Pattern pattern = Pattern.compile("This is root of mobile", Pattern.CASE_INSENSITIVE);
        explicitWait.until(ExpectedConditions.textMatches(By.id("sampleID"), pattern));
        // Wait until a element's text contain the given text
        explicitWait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.id("sampleID")), "Expected Text"));
        // Wait until the current url equal to the given string
        explicitWait.until(ExpectedConditions.urlToBe("https://vnexpress.net/"));
        // Wait until the current url contains the given string
        explicitWait.until(ExpectedConditions.urlContains("vnexpress.net"));
        // ???
        explicitWait.until(ExpectedConditions.urlMatches("[^abc]"));
        // ???
        explicitWait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeSelected(By.id("sampleID"))));
    }

    @AfterClass
    public void afterClass() {
        //driver.quit();
    }
}
