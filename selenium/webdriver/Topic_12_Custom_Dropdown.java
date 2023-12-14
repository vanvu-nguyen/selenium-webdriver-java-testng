package webdriver;

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

import java.time.Duration;
import java.util.List;

public class Topic_12_Custom_Dropdown {
    WebDriver driver;

    // Initialize for a WebDriverWait variable
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        // Declaration variable 'explicitWait' with the waiting duration for specified condition (will be set after action click dropdown)
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_JQuery() {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
        // Find the custom dropdown element then click on it
        driver.findElement(By.id("number-button")).click();
        // Wait until all items of dropdown listed on html
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//ul[@id='number-menu']/li")));
        // Declaration variable for dropdown all items
        List<WebElement> allItems = driver.findElements(By.xpath("//ul[@id='number-menu']/li"));
        // Looping each item using For Each function
        for (WebElement item : allItems) {
            String itemText = item.getText();
            System.out.println(itemText);
            if (itemText.equals("15")) {
                item.click();
                break;
            }
        }

        // Select a speed using custom function 'selectItemInDropdown'
        selectItemInDropdownJquery("speed-button", "//ul[@id='speed-menu']//div", "Fast");
        sleepInSecond(2);
        // Select a file
        selectItemInDropdownJquery("files-button", "//ul[@id='files-menu']//div", "Some unknown file");
        sleepInSecond(2);
        // Select a title
        selectItemInDropdownJquery("salutation-button", "//ul[@id='salutation-menu']//div", "Dr.");

        // Verify the expected selection
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='speed-button']/span[@class='ui-selectmenu-text']")).getText(), "Fast");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='files-button']/span[@class='ui-selectmenu-text']")).getText(), "Some unknown file");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "15");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='salutation-button']/span[@class='ui-selectmenu-text']")).getText(), "Dr.");
    }

    @Test
    public void TC_02_React() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        // First choice
        selectItemInDropdownReact("//i[@class='dropdown icon']", "//span[@class='text']", "Matt");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Matt");
        sleepInSecond(2);
        // Re-choose
        selectItemInDropdownReact("//i[@class='dropdown icon']", "//span[@class='text']", "Elliot Fu");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Elliot Fu");
        sleepInSecond(2);
        // Re-choose again
        selectItemInDropdownReact("//i[@class='dropdown icon']", "//span[@class='text']", "Stevie Feliciano");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Stevie Feliciano");
        sleepInSecond(2);
    }

    @Test
    public void TC_03_VueJS() {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");
        // First choice
        selectItemInDropdownReact("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "First Option");
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "First Option");
        sleepInSecond(2);
        // Re-choose
        selectItemInDropdownReact("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "Second Option");
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "Second Option");
        sleepInSecond(2);
        // Re-choose again
        selectItemInDropdownReact("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "Third Option");
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "Third Option");
        sleepInSecond(2);
    }

    @Test
    public void TC_04_EditableDropdown() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
        // First choice
        selectItemInEditableDropdown("//input[@class='search']", "//span[@class='text']", "Angola");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Angola");
        sleepInSecond(2);
        // Re-choose
        selectItemInEditableDropdown("//input[@class='search']", "//span[@class='text']", "Argentina");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Argentina");
        sleepInSecond(2);
        // Re-choose again
        selectItemInEditableDropdown("//input[@class='search']", "//span[@class='text']", "Belarus");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Belarus");
        sleepInSecond(2);
    }

    @Test
    public void TC_05_CustomFunction_ForDefaultDropdown() {
        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
        // Select date then verify
        selectItemInDropdownReact("//select[@name='DateOfBirthDay']", "//select[@name='DateOfBirthDay']/option", "18");
        Assert.assertTrue(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']/option[text()='18']")).isSelected());
        sleepInSecond(2);
        // Select month then verify
        selectItemInDropdownReact("//select[@name='DateOfBirthMonth']", "//select[@name='DateOfBirthMonth']/option", "May");
        Assert.assertTrue(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']/option[text()='May']")).isSelected());
        sleepInSecond(2);
        // Select year then verify
        selectItemInDropdownReact("//select[@name='DateOfBirthDay']", "//select[@name='DateOfBirthYear']/option", "1980");
        Assert.assertTrue(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']/option[text()='1980']")).isSelected());
        sleepInSecond(2);
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

    // Function for JQuery site
    public void selectItemInDropdownJquery (String parentId, String childXpath, String expectedItem) {
        driver.findElement(By.id(parentId)).click();
        // Merge wait and declare because 'explicitWait.until()' execute wait and find element then return a list
        List<WebElement> allItems =
                explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
        for (WebElement item : allItems) {
            String itemText = item.getText();
            System.out.println(itemText);
            if (itemText.equals(expectedItem)) {
                item.click();
                break;
            }
        }
    }

    // Function for React site -> Prefer using XPath for parameter of function
    public void selectItemInDropdownReact (String parentXpath, String childXpath, String expectedItem) {
        driver.findElement(By.xpath(parentXpath)).click();
        List<WebElement> allItems =
                explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
        for (WebElement item : allItems) {
            String itemText = item.getText();
            System.out.println(itemText);
            if (itemText.equals(expectedItem)) {
                item.click();
                break;
            }
        }
    }

    public void selectItemInEditableDropdown (String parentXpath, String childXpath, String expectedItem) {
        driver.findElement(By.xpath(parentXpath)).clear();
        driver.findElement(By.xpath(parentXpath)).sendKeys(expectedItem);
        List<WebElement> allItems =
                explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
        for (WebElement item : allItems) {
            String itemText = item.getText();
            System.out.println(itemText);
            if (itemText.equals(expectedItem)) {
                item.click();
                break;
            }
        }
    }

}

// Built the custom function should be for separate class/testcase. Should not reuse for other ones.


