package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Array;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Topic_16_Actions {
    WebDriver driver;
    Actions actions;
    JavascriptExecutor javascriptExecutor;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        actions = new Actions(driver);
        javascriptExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Tooltip() {
        driver.get("https://automationfc.github.io/jquery-tooltip/");
        WebElement ageInput = driver.findElement(By.id("age"));
        actions.moveToElement(ageInput).perform();
        sleepInSecond(1);
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText(), "We ask for your age only for statistical purposes.");
    }

/* This website blocks autotest
    @Test
    public void TC_02_Hover_MenuElement() {
        driver.get("https://www.myntra.com/");
        actions.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navLink']/a[text()='Kids']"))).perform();
        sleepInSecond(2);
        driver.findElement(By.xpath("//a[text()='Home & Bath']")).click();
        sleepInSecond(2);
        Assert.assertEquals(driver.findElement(By.xpath("(//ul[@class='breadcrumbs-list']/li)[last()]")).getText(),"Kids Home Bath");
        Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='title-title']")).getText(), "Kids Home Bath");
    }
*/

    @Test
    public void TC_02_Hover_MenuElement() {
        driver.get("https://www.fahasa.com/");
        actions.moveToElement(driver.findElement(By.xpath("//span[@class='icon_menu']"))).perform();
        sleepInSecond(2);
        actions.moveToElement(driver.findElement(By.xpath("//a[@title='Bách Hóa Online - Lưu Niệm']"))).perform();
        driver.findElement(By.xpath("//div[@class='fhs_menu_content fhs_column_left']//a[text()='Thiết Bị Số - Phụ Kiện Số']")).click();
        sleepInSecond(2);
        Assert.assertEquals(driver.findElement(By.xpath("//ol[@class='breadcrumb']//strong")).getText(), "THIẾT BỊ SỐ - PHỤ KIỆN SỐ");
    }

    @Test
    public void TC_03_Click_Hold() {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> options = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee']"));
        actions.clickAndHold(options.get(0)).moveToElement(options.get(14)).release().perform();
        List<WebElement> selectedElements = driver.findElements(By.xpath("//li[contains(@class,'ui-selected')]"));
        List<String> selectedTexts = new ArrayList<String>();
        for (WebElement element: selectedElements) {
            selectedTexts.add(element.getText());
        }
        List<String> expectedTexts = new ArrayList<String>();
        expectedTexts.add("1");
        expectedTexts.add("2");
        expectedTexts.add("3");
        expectedTexts.add("5");
        expectedTexts.add("6");
        expectedTexts.add("7");
        expectedTexts.add("9");
        expectedTexts.add("10");
        expectedTexts.add("11");
        expectedTexts.add("13");
        expectedTexts.add("14");
        expectedTexts.add("15");
        Assert.assertEquals(expectedTexts, selectedTexts);
    }

    @Test
    public void TC_02_Click_Hold_Command_Control() {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> options = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee']"));
        /* Click and hold option
        actions.clickAndHold(options.get(0)).moveToElement(options.get(11)).release().perform();
        */
        Keys keyCmdCtrl;
        String osName = System.getProperty("os.name");
        if (osName.startsWith("Window")) {
            keyCmdCtrl = Keys.CONTROL;
        } else {
            keyCmdCtrl = Keys.COMMAND;
        }
        /* And click the rest options
        actions.keyDown(keyCmdCtrl).perform();
        actions.click(options.get(12)).click(options.get(13)).click(options.get(14)).perform();
        actions.keyUp(keyCmdCtrl).perform();
        */
        // Loop for all option want to select
        actions.keyDown(keyCmdCtrl).perform();
        for (int i = 0; i < 15; i++) {
            actions.click(options.get(i)).perform();
        }
        actions.keyUp(keyCmdCtrl).perform();
        sleepInSecond(2);
        List<WebElement> selectedElements = driver.findElements(By.xpath("//li[contains(@class,'ui-selected')]"));
        List<String> selectedTexts = new ArrayList<String>();
        for (WebElement element: selectedElements) {
            selectedTexts.add(element.getText());
        }
        List<String> expectedTexts = new ArrayList<String>();
        expectedTexts.add("1");
        expectedTexts.add("2");
        expectedTexts.add("3");
        expectedTexts.add("4");
        expectedTexts.add("5");
        expectedTexts.add("6");
        expectedTexts.add("7");
        expectedTexts.add("8");
        expectedTexts.add("9");
        expectedTexts.add("10");
        expectedTexts.add("11");
        expectedTexts.add("12");
        expectedTexts.add("13");
        expectedTexts.add("14");
        expectedTexts.add("15");
        Assert.assertEquals(expectedTexts, selectedTexts);
    }

    @Test
    public void TC_03_Double_Click() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement doubleClickButton = driver.findElement(By.xpath("//button[@ondblclick='doubleClickMe()']"));
        if (driver.toString().contains("firefox")) {
            javascriptExecutor.executeScript("arguments[0].scrollIntoView(false);", doubleClickButton);
            sleepInSecond(2);
        }
        actions.doubleClick(doubleClickButton).perform();
        sleepInSecond(2);
        Assert.assertEquals(driver.findElement(By.id("demo")).getText(), "Hello Automation Guys!");
    }

    @Test
    public void TC_04_Context_Click() {
        driver.get("https://swisnl.github.io/jQuery-contextMenu/demo.html");
        actions.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
        sleepInSecond(1);
        Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-item')]")).isDisplayed());
        actions.moveToElement(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-paste')]"))).perform();
        sleepInSecond(1);
        Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-paste context-menu-hover context-menu-visible')]")).isDisplayed());
        actions.click(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-paste')]"))).perform();
        sleepInSecond(1);
        driver.switchTo().alert().accept();
        sleepInSecond(1);
        Assert.assertFalse(driver.findElement(By.xpath("//li[contains(@class,'context-menu-item')]")).isDisplayed());
    }

    @Test
    public void TC_05_DragAndDrop_HTML4() {
        driver.get("https://automationfc.github.io/kendo-drag-drop/");
        WebElement smallCircle = driver.findElement(By.id("draggable"));
        WebElement bigCircle = driver.findElement(By.id("droptarget"));
        actions.dragAndDrop(smallCircle, bigCircle).perform();
        Assert.assertEquals(bigCircle.getText(), "You did great!");
    }

    // TC_06_DragAndDrop_HTML5_CSS and TC_07_DragAndDrop_Xpath, see video [Online 29] - Topic 33(Actions - Part III). The action drag and drop is not recommended to implement auto test.

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
