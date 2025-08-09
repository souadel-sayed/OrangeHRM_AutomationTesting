package Orange.pages;

import Orange.drivers.GUIDriver;
import Orange.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.locators.RelativeLocator;

public class PIMPage {
    private final String PIMEndpoint = "/pim/viewEmployeeList";
    private final By employeeName = By.xpath("(//input[@placeholder='Type for hints...'])[1]");
    private final By SupervisorName = By.xpath("(//input[@placeholder='Type for hints...'])[2]");
    private final By EmployeeId = By.xpath("//label[contains(normalize-space(), 'Employee Id')]/following::input[1]");
    private final By Status = RelativeLocator.with(By.tagName("div")).below(By.xpath("//label[normalize-space()='Employment Status']"));
    private final By Include = RelativeLocator.with(By.tagName("div")).below(By.xpath("//label[normalize-space()='Include']"));
    private final By JobTitle = RelativeLocator.with(By.tagName("div")).below(By.xpath("//label[normalize-space()='Job Title']"));
    private final By SubUnit = RelativeLocator.with(By.tagName("div")).below(By.xpath("//label[normalize-space()='Sub Unit']"));
    private final By searchbutt = By.cssSelector("button[type='submit']");
    private final By message = By.cssSelector("div[class='orangehrm-horizontal-padding orangehrm-vertical-padding'] span[class='oxd-text oxd-text--span']");
    private final By options = By.xpath("(//div[@role='listbox' and contains(@class, 'oxd-autocomplete-dropdown')]//div[@role='option'])[1]");


    // add employee
    private final By addbutt = By.cssSelector("button[class='oxd-button oxd-button--medium oxd-button--secondary']");
    private final By employeeFirstName = By.xpath("//input[@placeholder='First Name']");
    private final By employeeMiddleName = By.xpath("//input[@placeholder='Middle Name']");
    private final By employeeLastName = By.xpath("//input[@placeholder='Last Name']");
    private final By employeeIdField = By.xpath("//label[text()='Employee Id']/following::input[1]");

    private final By lastNRequired = By.cssSelector("div[class='--name-grouped-field'] span[class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']");
    private final By saveButton = By.cssSelector("button[type='submit']");
    private final By successMessage = By.cssSelector(".oxd-text.oxd-text--p.oxd-text--toast-message.oxd-toast-content-text");
    private final By failureMessage = By.xpath("//span[normalize-space()='Required']");
    private final By userNameExists = By.cssSelector(".oxd-text.oxd-text--span.oxd-input-field-error-message.oxd-input-group__message");
    private final By employeeIdExits = By.cssSelector("div[class='oxd-grid-2 orangehrm-full-width-grid'] span[class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']");


    private GUIDriver driver;

    public PIMPage(GUIDriver driver) {
        this.driver = driver;
    }


    @Step("Navigate to Admin Page")
    public PIMPage navigate() {
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb") + PIMEndpoint);
        return this;
    }


    @Step("Search for Employee by Name")
    public PIMPage searchEmployeeByName(String name) {
        driver.element().handleAutoComplete(employeeName, name, options);
        return this;
    }

    @Step("Search for Employee by id")
    public PIMPage searchEmployeeById(String id) {
        driver.element().type(EmployeeId, id);
        return this;
    }

    @Step("click on Search Button")
    public PIMPage clickSearchButton() {
        driver.element().click(searchbutt);
        return this;
    }

    @Step("Get message sucess")
    public PIMPage FoundMess(String expectedMessage) {
        String actualMessage = driver.element().getText(message);
        driver.verification().assertTrue(actualMessage.contains(expectedMessage), "Success message contains expected text");
        System.out.println("Success message after adding user is as expected: " + actualMessage);
        return this;

    }

    @Step("Get message fail")
    public PIMPage NotFoundMess(String expectedMessage) {
        String actualMessage = driver.element().getText(message);
        driver.verification().assertTrue(actualMessage.contains(expectedMessage), "Not Found message contains expected text");
        System.out.println("Message after search: " + actualMessage);
        return this;

    }


    //ADD USER

    @Step("Click on Add Button")
    public PIMPage clickAddButton() {
        driver.element().click(addbutt);
        return this;
    }

    @Step("Enter Employee First Name")
    public PIMPage enterEmployeeFirstName(String firstName) {
        driver.element().type(employeeFirstName, firstName);
        return this;
    }

    @Step("Enter Employee Middle Name")
    public PIMPage enterEmployeeMiddleName(String middleName) {
        driver.element().type(employeeMiddleName, middleName);
        return this;
    }

    @Step("Enter Employee Last Name")
    public PIMPage enterEmployeeLastName(String lastName) {
        driver.element().type(employeeLastName, lastName);
        return this;
    }

    @Step("Enter Employee Id")
    public PIMPage enterEmployeeId(String employeeId) {
        driver.element().type(employeeIdField, employeeId);
        return this;
    }

    @Step("Click on Save Button")
    public PIMPage clickSaveButton() {
        driver.element().click(saveButton);
        return this;
    }

    @Step("Verify Success Message")
    public PIMPage verifySuccessMessage(String expectedMessage) {
        String actualMessage = driver.element().getText(successMessage);
        driver.verification().assertTrue(actualMessage.contains(expectedMessage), "Success message contains expected text");
        System.out.println("Success message after adding user is as expected: " + actualMessage);
        return this;
    }

    @Step("Verify Failure Message")
    public PIMPage verifyFailureMessage(String expectedMessage) {
        String actualMessage = driver.element().getText(failureMessage);
        driver.verification().assertTrue(actualMessage.contains(expectedMessage), "Failure message contains expected text");
        System.out.println("Failure message after adding user is as expected: " + actualMessage);
        return this;
    }

    @Step("Verify User Name Exists Message")
    public PIMPage verifyUserNameExistsreq(String expectedMessage) {
        String actualMessage = driver.element().getText(lastNRequired);
        driver.verification().assertTrue(actualMessage.contains(expectedMessage), "User name exists message contains expected text");
        System.out.println("User name exists message is as expected: " + actualMessage);
        return this;
    }

    @Step("Verify Employee Id Exists Message")
    public PIMPage verifyEmployeeIdExistsMessage(String expectedMessage) {
        String actualMessage = driver.element().getText(employeeIdExits);
        driver.verification().assertTrue(actualMessage.contains(expectedMessage), "Employee ID exists message contains expected text");
        System.out.println("Employee ID exists message is as expected: " + actualMessage);
        return this;
    }


}
