package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.xml.sax.Locator;

import java.time.Duration;

public class Topic_19_Shadow_DOM {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_() {
        driver.get("https://automationfc.github.io/shadow-dom/");
        WebElement shadowHostElement1 = driver.findElement(By.id("shadow_host"));
        SearchContext shadowRoot1 = shadowHostElement1.getShadowRoot();
        Assert.assertEquals(shadowRoot1.findElement(By.cssSelector("span.info")).getText(), "some text");
        WebElement shadowHostElement2 = shadowRoot1.findElement(By.cssSelector("div#nested_shadow_host"));
        SearchContext shadowRoot2 = shadowHostElement2.getShadowRoot();
        Assert.assertEquals(shadowRoot2.findElement(By.cssSelector("div#nested_shadow_content>div")).getText(), "nested text");
    }

    @Test
    public void TC_02_() {
        driver.get("https://shopee.vn/");
        sleepInSecond(3);
        WebElement shadowHost1 = driver.findElement(By.cssSelector("shopee-banner-popup-stateful"));
        SearchContext shadowRoot1 = shadowHost1.getShadowRoot();
        WebElement shadowHost2 = shadowRoot1.findElement(By.cssSelector("shopee-banner-simple"));
        SearchContext shadowRoot2 = shadowHost2.getShadowRoot();
        By bannerImage = By.cssSelector("img.banner-image");
        if (!shadowRoot2.findElements(bannerImage).isEmpty() && shadowRoot2.findElements(bannerImage).get(0).isDisplayed()) {
            shadowRoot1.findElement(By.cssSelector("div.shopee-popup__close-btn")).click();
            sleepInSecond(3);
        }
        driver.findElement(By.className("shopee-searchbar-input__input")).sendKeys("iPhone 15 Pro Max");
        driver.findElement(By.className("shopee-searchbar__search-button")).click();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void sleepInSecond (long timeInSecond) {
        try {
            Thread.sleep(timeInSecond*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
