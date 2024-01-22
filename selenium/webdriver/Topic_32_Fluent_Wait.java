package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.function.Function;

public class Topic_32_Fluent_Wait {
    WebDriver driver;
    FluentWait fluentDriver;
    FluentWait<WebElement> fluentElement;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        fluentDriver = new FluentWait<WebDriver>(driver);
    }

    @Test
    public void TC_01_() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.xpath("//div[@id='start']/button")).click();

        // Setting
        fluentDriver.withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class, TimeoutException.class);
        // Condition
        fluentDriver.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return driver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed();
            }
        });
    }

    @Test
    public void TC_02_() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.xpath("//div[@id='start']/button")).click();

        // Setting
        fluentDriver.withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class, TimeoutException.class);
        // Condition
        String finishText = (String) fluentDriver.until(new Function<WebDriver, String>() {
            @Override
            public String apply(WebDriver driver) {
                return driver.findElement(By.xpath("//div[@id='finish']/h4")).getText();
            }
        });
        System.out.println(finishText);
        Assert.assertEquals(finishText, "Hello World!");
    }

    @Test
    public void TC_03_() {
        driver.get("https://automationfc.github.io/fluent-wait/");
        fluentDriver.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);
        fluentDriver.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return driver.findElement(By.xpath("//div[@id='javascript_countdown_time']")).isDisplayed();
            }
        });

        fluentDriver.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return driver.findElement(By.xpath("//div[text()='01:01:00']")).isDisplayed();
            }
        });
        System.out.println(driver.findElement(By.xpath("//div[@id='javascript_countdown_time']")).getText());
    }

    @Test
    public void TC_04_() {
        driver.get("https://automationfc.github.io/fluent-wait/");
        WebElement countDown = driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));
        fluentElement = new FluentWait<WebElement>(countDown);
        fluentElement.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);
        fluentElement.until(new Function<WebElement, Boolean>() {
            @Override
            public Boolean apply(WebElement webElement) {
                String text = countDown.getText();
                System.out.println(text);
                return text.endsWith("00");
            }
        });
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
