package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_00_Test {
    WebDriver driver;

    @Test
    public void TC_01_() {
        driver.get("https://www.facebook.com/");
        Point vuNguyen = driver.findElement(By.id("pass")).getLocation();
        System.out.println(vuNguyen.getX());
    }
}
