package javaTester;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_05_Assert {
    WebDriver driver
    /*
    This lesson is about the verified functions of TestNG library
        - Java has many libraries support to verify data called 'Testing Framework'
        - These Testing Framework can be applied for the testing level: Unit, Integration, UI automation test
        - The most helpful framework: JUnit 4, TestNG, JUnit 4, Hamcrest, AssertJ, ...
    */

    @Test
    public void TestNGVerifiedFunction() {
        driver = new FirefoxDriver();
        driver.get("https://www.facebook.com/");

        // TestNG function returns boolean data-type:
        // - assertTrue returns value True -> Pass
        Assert.assertTrue(driver.getPageSource().contains("Facebook helps you connect and share with the people in your life."));
        // - assertFalse return value False -> Pass
        Assert.assertFalse(driver.getPageSource().contains("It's quick and easy."));


        // - assertNull and assertNotNull are for checking value of a variable is null or not null
        Assert.assertNull("");
        Assert.assertNotNull("");

        // Selenium function returns boolean data-type:
        driver.findElement(By.id("")).isDisplayed();
        driver.findElement(By.id("")).isEnabled();
        driver.findElement(By.id("")).isSelected();
        new Select(driver.findElement(By.id(""))).isMultiple();

    }
}
