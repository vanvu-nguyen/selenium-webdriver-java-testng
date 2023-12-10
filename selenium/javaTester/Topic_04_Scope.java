package javaTester;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_04_Scope {
    WebDriver driver;
    // Declaration for a global variable (outside of every function) that can be used for any function in class
    String homePageUrl = "https://www.youtube.com/";
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
    }

    @Test
    public void TC_01_() {
        // Declare and initialize a local variable inside a function/block of code which is only used for the function
        String homePageUrl = "https://www.facebook.com/";
        /*
        Incase a local variable has the same name with a global variable, the function contains the local variable will get the local variable to execute
        It will occur error when the called variable have not been initiallized:
        - Local variable: warning in Compile-code period
        - Global variable: warning in Runtime period
        */
        driver.get(homePageUrl);
        // Use 'this' keyword if using the global variable
        driver.get(this.homePageUrl);

    }

    @Test
    public void TC_02_() {
    }

    @Test
    public void TC_03_() {
    }

    @Test
    public void TC_04_() {
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

