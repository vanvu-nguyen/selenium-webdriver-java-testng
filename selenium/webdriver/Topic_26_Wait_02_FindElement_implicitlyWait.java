package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_26_Wait_02_FindElement_implicitlyWait {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.xpath("//button[text()='Start']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

        /*
        1. findElement()
            - Find THE FIRST ELEMENT matches locator
            - Retry to find every 500ms if still not find out the element until run out of implicitWait duration
            - Return an element if find out, no need to wait until run out of implicitWait, then implement the next script
            - If still can not find out the element after implicitWait run out, testcase fail at the findElement step, skip the rest script
        2. findElements()
            - Find all element match locator
            - Retry to find every 500ms if still not find out the element until run out of implicitWait duration
            - Return a list of element if find out, no need to wait until run out of implicitWait, then implement the next script
            - If still can not find out the element after implicitWait run out, return an empty list and then implement the next script
        3. About implicitlyWait(), if the argument is negative/not null/greater than 2e16-1, system will throw an exception with invalid argument
        */
