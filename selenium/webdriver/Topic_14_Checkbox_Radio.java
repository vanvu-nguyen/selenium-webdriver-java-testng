package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_14_Checkbox_Radio {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Checkbox() {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        WebElement rearSideCB = driver.findElement(By.xpath("(//label[text()='Rear side airbags']/preceding-sibling::input)[last()]"));
        WebElement dualZoneCB = driver.findElement(By.xpath("(//label[text()='Dual-zone air conditioning']/preceding-sibling::input)[last()]"));

/* Solution 1
        if (!rearSideCB.isSelected()) {
            rearSideCB.click();
            sleepInSecond(2);
        }
        if (!dualZoneCB.isSelected()) {
            dualZoneCB.click();
            sleepInSecond(2);
        }
        Assert.assertTrue(rearSideCB.isSelected());
        Assert.assertTrue(dualZoneCB.isSelected());

*/

        // Solution 2 (Execute function)
        checkCheckboxAction(dualZoneCB);
        uncheckCheckboxAction(rearSideCB);
        Assert.assertTrue(dualZoneCB.isSelected());
        Assert.assertFalse(rearSideCB.isSelected());

        checkCheckboxAction(rearSideCB);
        uncheckCheckboxAction(dualZoneCB);
        Assert.assertTrue(rearSideCB.isSelected());
        Assert.assertFalse(dualZoneCB.isSelected());
    }

    @Test
    public void TC_02_Radio() {
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
        WebElement pe20_147kw = driver.findElement(By.xpath("(//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input)[last()]"));
        WebElement di16_77kw = driver.findElement(By.xpath("(//label[text()='1.6 Diesel, 77kW']/preceding-sibling::input)[last()]"));
        checkRadioAction(pe20_147kw);
        Assert.assertTrue(pe20_147kw.isSelected());
        Assert.assertFalse(di16_77kw.isSelected());
        checkRadioAction(di16_77kw);
        Assert.assertFalse(pe20_147kw.isSelected());
        Assert.assertTrue(di16_77kw.isSelected());
    }

    @Test
    public void TC_03_Select_All_Checkbox() {
        driver.get("https://automationfc.github.io/multiple-fields/");
        List<WebElement> allCheckboxes = driver.findElements(By.xpath("//label[text()=' Have you ever had (Please check all that apply) ']/following-sibling::div//input"));
        for (WebElement item: allCheckboxes) {
            if (!item.isSelected()) {
                item.click();
            }
        }
        for (WebElement checkedItem: allCheckboxes) {
            Assert.assertTrue(checkedItem.isSelected());
        }

        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        allCheckboxes = driver.findElements(By.xpath("//label[text()=' Have you ever had (Please check all that apply) ']/following-sibling::div//input"));

        for (WebElement keyItem: allCheckboxes) {
            if (keyItem.getAttribute("value").equals("Heart Attack") && !keyItem.isSelected()) {
                keyItem.click();
            } else {
                keyItem.click();
                keyItem.click();
            }
        }

        for (WebElement eachItem: allCheckboxes) {
            if (eachItem.getAttribute("value").equals("Heart Attack")) {
                Assert.assertTrue(eachItem.isSelected());
            } else {
                Assert.assertFalse(eachItem.isSelected());
            }
        }
    }

    @Test
    public void TC_04_Custom_Radio() {
        driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
        WebElement targetRadioButton = driver.findElement(By.xpath("//input[@id='mat-radio-3-input']"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", targetRadioButton);
        Assert.assertTrue(targetRadioButton.isSelected());
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

    public void checkCheckboxAction(WebElement checkCBElement) {
        if (!checkCBElement.isSelected()) {
            checkCBElement.click();
            sleepInSecond(2);
        }
    }

    public void uncheckCheckboxAction(WebElement uncheckCBElement) {
        if (uncheckCBElement.isSelected()) {
            uncheckCBElement.click();
            sleepInSecond(2);
        }
    }

    public void checkRadioAction (WebElement checkRadioElement) {
        if (!checkRadioElement.isSelected()) {
            checkRadioElement.click();
            sleepInSecond(2);
        }
    }
}
