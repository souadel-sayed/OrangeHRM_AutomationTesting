package Orange.utils.actions;


import Orange.utils.WaitManager;
import Orange.utils.logs.LogsManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

public class ElementActions {
    private final WebDriver driver;
    private WaitManager waitManager;

    public ElementActions(WebDriver driver) {
        this.driver = driver;
        this.waitManager = new WaitManager(driver);
    }

    //Clicking
    public ElementActions click(By locator) {
        waitManager.fluentWait().until(d ->
                {
                    try {
                        WebElement element = d.findElement(locator);
                        scrollToElementJS(locator);
                        // Wait until the element is stable (not moving)
                        Point initialLocation = element.getLocation();
                        LogsManager.info("initialLocation: " + initialLocation);
                        Point finalLocation = element.getLocation();
                        LogsManager.info("finalLocation: " + finalLocation);
                        if (!initialLocation.equals(finalLocation)) {
                            return false; // still moving, wait longer
                        }
                        element.click();
                        LogsManager.info("Clicked on element: " + locator);
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                }
        );
        return this;
    }

    //Typing
    public ElementActions type(By locator, String text) {
        waitManager.fluentWait().until(d ->
                {
                    try {
                        WebElement element = d.findElement(locator);
                        scrollToElementJS(locator);
                        element.clear();
                        element.sendKeys(text);
                        LogsManager.info("Typed text '" + text + "' into element: " + locator);
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                }
        );
        return this;
    }

    //hovering
    public ElementActions hover(By locator) {
        waitManager.fluentWait().until(d ->
                {
                    try {
                        WebElement element = d.findElement(locator);
                        scrollToElementJS(locator);
                        new Actions(d).moveToElement(element).perform();
                        LogsManager.info("Hovered over element: " + locator);
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                }
        );
        return this;
    }

    //Getting text
    public String getText(By locator) {
        return waitManager.fluentWait().until(d ->
                {
                    try {
                        WebElement element = d.findElement(locator);
                        scrollToElementJS(locator);
                        String msg = element.getText();
                        LogsManager.info("Retrieved text from element: " + locator + " - Text: " + msg);
                        return !msg.isEmpty() ? msg : null;
                    } catch (Exception e) {
                        return null;
                    }
                }
        );
    }

    //upload file
    public ElementActions uploadFile(By locator, String filePath) {
        String fileAbsolute = System.getProperty("user.dir") + File.separator + filePath;
        waitManager.fluentWait().until(d ->
                {
                    try {
                        WebElement element = d.findElement(locator);
                        scrollToElementJS(locator);
                        element.sendKeys(fileAbsolute);
                        LogsManager.info("Uploaded file: " + fileAbsolute + " to element: " + locator);
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                }
        );
        return this;
    }


    //find an element
    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    //function to scroll to an element using js
    public void scrollToElementJS(By locator) {
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript(""" 
                        arguments[0].scrollIntoView({behaviour:"auto",block:"center",inline:"center"});""", findElement(locator));
    }

    //select from dropdown
    public ElementActions selectFromDropdown(By locator, String value) {
        waitManager.fluentWait().until(d ->
                {
                    try {
                        WebElement element = d.findElement(locator);
                        scrollToElementJS(locator);
                        Select select = new Select(element);
                        select.selectByVisibleText(value);
                        LogsManager.info("Selected value '" + value + "' from dropdown: " + locator);
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                }
        );
        return this;
    }

    // In ElementActions.java

    public ElementActions selectOptionFromCustomDropdown(By locator, String value) {
        waitManager.fluentWait().until(d -> d.findElement(locator).isDisplayed());
        scrollToElementJS(locator);
        WebElement dropdown = findElement(locator);
        dropdown.click();

        // Adjust the XPath if your dropdown structure is different
        By optionLocator = By.xpath(String.format(
                "//div[@role='option'][normalize-space()='%s']",
                value.replace("'", "\\'")
        ));
        waitManager.fluentWait().until(d -> d.findElement(optionLocator).isDisplayed());
        WebElement option = driver.findElement(optionLocator);
        option.click();

        LogsManager.info("Selected option: " + value + " from dropdown: " + locator);
        return this;
    }

    public void pressKey(By employeeName, String arrowDown) {

        waitManager.fluentWait().until(d ->
                {
                    try {
                        WebElement element = d.findElement(employeeName);
                        scrollToElementJS(employeeName);
                        new Actions(d).moveToElement(element).sendKeys(arrowDown).perform();
                        LogsManager.info("Pressed key '" + arrowDown + "' on element: " + employeeName);
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                }
        );
    }


    @io.qameta.allure.Step("Handle AutoComplete: {locator} with value: {value}")
    public ElementActions handleAutoComplete(By locator, String value, By optionLocator) {
        waitManager.fluentWait().until(d -> d.findElement(locator).isDisplayed());
        scrollToElementJS(locator);
        WebElement autoCompleteField = findElement(locator);
        autoCompleteField.click();
        autoCompleteField.clear();
        autoCompleteField.sendKeys(value);
        waitManager.fluentWait().until(d -> d.findElement(optionLocator).isDisplayed());
        findElement(optionLocator).click();
        LogsManager.info("Handled AutoComplete for: " + locator + " with value: " + value);
        return this;
    }


    @io.qameta.allure.Step("Sending data to element: {locator} with data: {data}")
    public ElementActions sendDataToElement(By locator, String data) {
        waitManager.fluentWait().until(d -> d.findElement(locator).isDisplayed());
        scrollToElementJS(locator);
        WebElement element = findElement(locator);
        element.sendKeys(org.openqa.selenium.Keys.CONTROL + "a");
        element.sendKeys(org.openqa.selenium.Keys.DELETE);
        element.sendKeys(data);
        LogsManager.info("Sending data to element: " + locator + " with data: " + data);
        return this;
    }
}
