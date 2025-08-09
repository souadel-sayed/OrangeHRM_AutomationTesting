package Orange.pages;

import Orange.drivers.GUIDriver;
import Orange.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class TimePage {

    private final String TimeEndpoint = "/time/viewEmployeeTimesheet";
    private final By employeeName = By.cssSelector("input[placeholder='Type for hints...']");
    private final By viewbutton = By.cssSelector("button[type='submit']");
    private final By sucessview = By.cssSelector(".oxd-text.oxd-text--h6.orangehrm-main-title");
    private final By errorView = By.cssSelector(".oxd-text.oxd-text--p.oxd-alert-content-text");
    private final By options = By.xpath("//div[contains(@class, 'oxd-autocomplete-dropdown') and @role='listbox']//div[@role='option']//span");


    private GUIDriver driver;

    public TimePage(GUIDriver driver) {
        this.driver = driver;
    }


    @Step("Navigate to Admin Page")
    public TimePage navigate() {
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb") + TimeEndpoint);
        return this;
    }

    @Step("Enter Employee Name")
    public TimePage enterEmployeeName(String name) {
        driver.element().handleAutoComplete(employeeName, name, options);
        return this;
    }

    @Step("Click View Button")
    public TimePage clickViewButton() {
        driver.element().click(viewbutton);
        return this;
    }

    @Step("Verify Successful View")
    public TimePage verifySuccessfulView(String expectedText) {
        String actualMessage = driver.element().getText(sucessview);
        driver.verification().assertTrue(actualMessage.contains(expectedText), "Usertime already exists  ");
        System.out.println("Usertime already exists : " + actualMessage);
        return this;
    }

    @Step("Verify notSuccessful View")
    public TimePage verifyNotSuccessfulView(String expectedText) {
        String actualMessage = driver.element().getText(errorView);
        driver.verification().assertTrue(actualMessage.contains(expectedText), "Usertime  not already exists  ");
        System.out.println("Usertime not already exists : " + actualMessage);
        return this;
    }

}
