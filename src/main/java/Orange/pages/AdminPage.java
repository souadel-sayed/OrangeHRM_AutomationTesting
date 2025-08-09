package Orange.pages;

import Orange.drivers.GUIDriver;
import Orange.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.locators.RelativeLocator;

public class AdminPage {

    private final String AdminEndpoint = "/admin/viewSystemUsers";
    //locators
    private final By AddButton = By.xpath("//button[normalize-space()='Add']");
    private final By EmployeeName = By.cssSelector("input[placeholder='Type for hints...']");
    private final By UserRole = RelativeLocator.with(By.tagName("div")).below(By.xpath("//label[normalize-space()='User Role']"));
    private final By Status = RelativeLocator.with(By.tagName("div")).below(By.xpath("//label[normalize-space()='Status']"));
    private final By Username = RelativeLocator.with(By.tagName("input")).below(By.xpath("//label[normalize-space()='Username']"));
    private final By Password = RelativeLocator.with(By.tagName("input")).below(By.xpath("//label[normalize-space()='Password']"));
    private final By ConfirmPassword = RelativeLocator.with(By.tagName("input")).below(By.xpath("//label[normalize-space()='Confirm Password']"));
    private final By SaveButton = By.cssSelector("button[type='submit']");


    private final By usernameExist = By.xpath("//span[normalize-space()='Required']");
    private final By passnotmatch = By.xpath("//span[normalize-space()='Passwords do not match']");
    private final By statRequerid = By.cssSelector("div[class='oxd-form-row'] span[class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']");
    private final By sucessmes = By.cssSelector(".oxd-text.oxd-text--p.oxd-text--toast-message.oxd-toast-content-text");
    private final By options = By.xpath("//div[contains(@class,'oxd-autocomplete-dropdown')]//div[@role='option']//span");


    //search locators
    private final By employnamee = By.cssSelector("input[placeholder='Type for hints...']");
    private final By userroleSearch = RelativeLocator.with(By.tagName("div")).below(By.xpath("//label[normalize-space()='User Role']"));
    private final By statusSearch = RelativeLocator.with(By.tagName("div")).below(By.xpath("//label[normalize-space()='Status']"));
    private final By usernameSearch = By.cssSelector("div.oxd-input-group > div > input");
    private final By searchButton = By.cssSelector("button[type='submit']");
    private final By resetButton = By.cssSelector("button[class='oxd-button oxd-button--medium oxd-button--ghost']");
    private final By RecordFound = By.cssSelector("span[class='oxd-text oxd-text--span']");
    private final By NoRecordsFound = By.cssSelector("div[class='orangehrm-horizontal-padding orangehrm-vertical-padding'] span[class='oxd-text oxd-text--span']");
    private final By optionssearch = By.xpath("//div[@role='listbox' and contains(@class, 'oxd-autocomplete-dropdown')]//div[@role='option']");

    //Delete locators
    private final By selectButton = By.cssSelector("body > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > label:nth-child(1) > span:nth-child(2) > i:nth-child(1)");
    private final By deleteButton = By.cssSelector("button[class='oxd-button oxd-button--medium oxd-button--label-danger orangehrm-horizontal-margin']");
    private final By deleteConfirmButton = By.cssSelector("button[class='oxd-button oxd-button--medium oxd-button--label-danger orangehrm-button-margin']");
    private final By deleteSuccessMessage = By.cssSelector(".oxd-toast.oxd-toast--success.oxd-toast-container--toast");

    //Edit locators
    private final By editButton = By.cssSelector("div:nth-child(4) div:nth-child(1) div:nth-child(6) div:nth-child(1) button:nth-child(2) i:nth-child(1)");
    private final By editname = By.cssSelector("input[placeholder='Type for hints...']");
    private final By savebutton = By.cssSelector("button[type='submit']");
    private final By requiredfield = By.cssSelector("body > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > form:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > span:nth-child(3)");
    private final By editSuccessMessage = By.cssSelector(".oxd-toast.oxd-toast--success.oxd-toast-container--toast");
    private final By editStatus = RelativeLocator.with(By.tagName("div")).below(By.xpath("//label[normalize-space()='Status']"));


    private GUIDriver driver;

    public AdminPage(GUIDriver driver) {
        this.driver = driver;
    }


    @Step("Navigate to Admin Page")
    public AdminPage navigate() {
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb") + AdminEndpoint);
        return this;
    }

    // actions for add new user
    @Step("Click Add Button")
    public AdminPage clickAddButton() {
        driver.element().click(AddButton);
        return this;
    }

    @Step("select user Status {role}")
    public AdminPage selectUserRole(String role) {
        driver.element().selectOptionFromCustomDropdown(UserRole, role);
        return this;
    }

    @Step("select user Status {status}")
    public AdminPage selectStatus(String status) {
        driver.element().selectOptionFromCustomDropdown(Status, status);
        return this;
    }

    @Step("Enter Employee name {employeeName} in login field")
    public AdminPage Enteremoloyrrname(String employeeName) {
        driver.element().handleAutoComplete(EmployeeName, employeeName, options);

        return this;
    }

    @Step("Enter Username {username} in login field")
    public AdminPage EnterUsername(String username) {
        driver.element().type(Username, username);
        return this;
    }

    @Step("Enter Password {password} in login field")
    public AdminPage EnterPassword(String password) {
        driver.element().sendDataToElement(Password, password);
        return this;
    }

    @Step("Enter Confirm Password {confirmPassword} in login field")
    public AdminPage EnterConfirmPassword(String confirmPassword) {
        driver.element().sendDataToElement(ConfirmPassword, confirmPassword);
        return this;
    }

    @Step("Click Save Button")
    public AdminPage clickSaveButton() {
        driver.element().click(SaveButton);
        return this;
    }

    @Step("Verify Username already exists error message")
    public AdminPage verifyUsernameExistError(String expectedMessage) {
        String actualMessage = driver.element().getText(usernameExist);
        driver.verification().Equals(actualMessage, expectedMessage, "Username already exists error message is as expected");
        System.out.println("Username already exists error message is as expected: " + actualMessage);
        return this;
    }


    @Step("Verify Passwords do not match error message")
    public AdminPage verifyPasswordNotMatchError(String expectedMessage) {
        String actualMessage = driver.element().getText(passnotmatch);
        driver.verification().Equals(actualMessage, expectedMessage, "Passwords do not match error message is as expected");
        System.out.println("Passwords do not match error message is as expected: " + actualMessage);
        return this;
    }

    @Step("Verify Status is required error message")
    public AdminPage verifyStatusRequiredError(String expectedMessage) {
        String actualMessage = driver.element().getText(statRequerid);
        driver.verification().Equals(actualMessage, expectedMessage, "Status is required error message is as expected");
        System.out.println("Status is required error message is as expected: " + actualMessage);
        return this;
    }

    @Step("Verify Success Message after adding user")
    public AdminPage verifySuccessMessage(String expectedMessage) {
        String actualMessage = driver.element().getText(sucessmes);
        // driver.verification().Equals(actualMessage, expectedMessage, "Success message after adding user is as expected");
        driver.verification().assertTrue(actualMessage.contains(expectedMessage), "Success message contains expected text");
        // Log the success message
        System.out.println("Success message after adding user is as expected: " + actualMessage);
        return this;
    }

    // actions for search user
    @Step("Enter Employee name {employeeName} in search field")
    public AdminPage searchEmployeeName(String employeeName) {
        driver.element().handleAutoComplete(employnamee, employeeName, optionssearch);
        return this;
    }

    @Step("Select User Role {role} in search field")
    public AdminPage searchUserRole(String role) {
        driver.element().selectOptionFromCustomDropdown(userroleSearch, role);
        return this;
    }

    @Step("Select Status {status} in search field")
    public AdminPage searchStatus(String status) {
        driver.element().selectOptionFromCustomDropdown(statusSearch, status);
        return this;
    }

    @Step("Enter Username {username} in search field")
    public AdminPage searchUsername(String username) {
        driver.element().type(usernameSearch, username);
        return this;
    }

    @Step("Click Search Button")
    public AdminPage clickSearchButton() {
        driver.element().click(searchButton);
        return this;
    }

    @Step("Click Reset Button")
    public AdminPage clickResetButton() {
        driver.element().click(resetButton);
        return this;
    }

    @Step("Verify No Records Found message")
    public AdminPage verifyNoRecordsFound(String expectedMessage) {
        String actualMessage = driver.element().getText(NoRecordsFound);
        driver.verification().Equals(actualMessage, expectedMessage, "No Records Found message is as expected");
        System.out.println("No Records Found message is as expected: " + actualMessage);
        return this;
    }

    @Step("Verify  Records Found message")
    public AdminPage verifyRecordsFound(String expectedMessage) {
        String actualMessage = driver.element().getText(NoRecordsFound);
        driver.verification().assertTrue(actualMessage.contains(expectedMessage), " Records Found message is as expected");
        System.out.println(" Records Found message is as expected: " + actualMessage);
        return this;
    }


    // actions for delete user
    @Step("Select User for deletion")
    public AdminPage selectUserForDeletion() {
        driver.element().click(selectButton);
        return this;
    }

    @Step("Click Delete Button")
    public AdminPage clickDeleteButton() {
        driver.element().click(deleteButton);
        return this;
    }

    @Step("Click Confirm Delete Button")
    public AdminPage clickConfirmDeleteButton() {
        driver.element().click(deleteConfirmButton);
        return this;
    }

    @Step("Verify Delete Success Message")
    public AdminPage verifyDeleteSuccessMessage(String expectedMessage) {
        String actualMessage = driver.element().getText(deleteSuccessMessage);
        driver.verification().assertTrue(actualMessage.contains(expectedMessage), "Delete success message is as expected");
        System.out.println("Delete success message is as expected: " + actualMessage);
        return this;
    }

    // actions for edit user

    @Step("Click Edit Button")
    public AdminPage clickEditButton() {
        driver.element().click(editButton);
        return this;
    }

    @Step("Enter Employee name {employeeName} in edit field")
    public AdminPage editStatus(String status) {
        driver.element().selectOptionFromCustomDropdown(editStatus, status);
        return this;
    }

    @Step("Click Save Button after editing")
    public AdminPage clickSaveButtonAfterEdit() {
        driver.element().click(savebutton);
        return this;
    }

    @Step("Verify Required Field Error Message")
    public AdminPage verifyRequiredFieldError(String expectedMessage) {
        String actualMessage = driver.element().getText(requiredfield);
        driver.verification().Equals(actualMessage, expectedMessage, "Required field error message is as expected");
        System.out.println("Required field error message is as expected: " + actualMessage);
        return this;
    }

    @Step("Verify Edit Success Message")
    public AdminPage verifyEditSuccessMessage(String expectedMessage) {
        String actualMessage = driver.element().getText(editSuccessMessage);
        driver.verification().assertTrue(actualMessage.contains(expectedMessage), "Edit success message is as expected");
        System.out.println("Edit success message is as expected: " + actualMessage);
        return this;
    }


}
