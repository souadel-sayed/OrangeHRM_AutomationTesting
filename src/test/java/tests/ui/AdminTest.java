package tests.ui;

import Orange.drivers.GUIDriver;
import Orange.drivers.UITest;
import Orange.pages.AdminPage;
import Orange.pages.SignupLoginPage;
import Orange.utils.TimeManager;
import Orange.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.BaseTest;

@Epic("OrangeHRM Automation")
@Feature("UI User Management")
@Story("Admin Dashboard")
@Severity(SeverityLevel.CRITICAL)
@Owner("Souad")
@UITest
public class AdminTest extends BaseTest {
    String timestamp = TimeManager.getSimpleTimestamp();

    @Description("Verify Add New User")
    @Test(priority = 1)
    public void addNewUserSuccessfullyTC() {

        new SignupLoginPage(driver).navigate()
                .enterLoginusername("Admin")
                .enterLoginPassword("admin123")
                .clickLoginButton()
                .verifyUserIsLoggedIn();
        // Add logic to navigate to Admin section and add a new user
        new AdminPage(driver).navigate()
                .clickAddButton()
                .selectUserRole(testData.getJsonData("Role"))
                .selectStatus(testData.getJsonData("Status"))
                .Enteremoloyrrname(testData.getJsonData("EmployeeName"))
                .EnterUsername(testData.getJsonData("Username"))
                .EnterPassword(testData.getJsonData("Password"))
                .EnterConfirmPassword(testData.getJsonData("Congresswoman"))
                .clickSaveButton()
                .verifySuccessMessage(testData.getJsonData("messages.successMessage"));

    }


  /*  @Description("Verify Add New User with invalid data")
    @Test
    public void addNewUserWithInvalidDataTC() {

        new AdminPage(driver).navigate()
                .clickAddButton()
                .selectUserRole(testData.getJsonData("Role"))
                .selectStatus(testData.getJsonData("Status"))
                .Enteremoloyrrname(testData.getJsonData("EmployeeName"))
                .EnterUsername(testData.getJsonData("Username"))
                .EnterPassword(testData.getJsonData("Password"))
                .EnterConfirmPassword(testData.getJsonData("Congresswoman"))
                .clickSaveButton()
                .verifyUsernameExistError(testData.getJsonData("messages.ExistUserMessage"));
    }*/

    @Description("Verify Add New User with empty fields")
    @Test(priority = 2)
    public void addNewUserWithEmptyFieldsTC() {

        new SignupLoginPage(driver).navigate()
                .enterLoginusername("Admin")
                .enterLoginPassword("admin123")
                .clickLoginButton()
                .verifyUserIsLoggedIn();
        new AdminPage(driver).navigate()
                .clickAddButton()
                .selectUserRole(testData.getJsonData("Role"))
                .selectStatus(testData.getJsonData("Status"))
                .Enteremoloyrrname(testData.getJsonData("EmployeeName"))
                .EnterPassword(testData.getJsonData("Password"))
                .EnterConfirmPassword(testData.getJsonData("Congresswoman"))
                .clickSaveButton()
                .verifyStatusRequiredError(testData.getJsonData("messages.EmptyField"));

    }

    @Description("Verify Confirm Password does not match Password")
    @Test(priority = 3)
    public void confirmPasswordDoesNotMatchPasswordTC() {

        new SignupLoginPage(driver).navigate()
                .enterLoginusername("Admin")
                .enterLoginPassword("admin123")
                .clickLoginButton()
                .verifyUserIsLoggedIn();

        new AdminPage(driver).navigate()
                .clickAddButton()
                .selectUserRole(testData.getJsonData("Role"))
                .selectStatus(testData.getJsonData("Status"))
                .Enteremoloyrrname(testData.getJsonData("EmployeeName"))
                .EnterUsername(testData.getJsonData("Username"))
                .EnterPassword(testData.getJsonData("Password"))
                .EnterConfirmPassword(testData.getJsonData("InconfirmPassword"))
                .clickSaveButton()
                .verifyPasswordNotMatchError(testData.getJsonData("messages.ConfirmPasswordError"));
    }


    // Search User
    @Description("Verfiy Search about existant user")
    @Test(priority = 4)
    public void searchExistantUserTC() {
        new SignupLoginPage(driver).navigate()
                .enterLoginusername("Admin")
                .enterLoginPassword("admin123")
                .clickLoginButton()
                .verifyUserIsLoggedIn();
        new AdminPage(driver).navigate()
                .searchUsername(testData.getJsonData("searchAdminUser.Username"))
                .selectUserRole(testData.getJsonData("searchAdminUser.UserRole"))
                .searchStatus(testData.getJsonData("searchAdminUser.Status"))
                .clickSearchButton()
                .verifyRecordsFound(testData.getJsonData("messagesforSearch.successMessage"));
    }


    @Description("Verify Search with incorrect data")
    @Test(priority = 5)
    public void searchWithIncorrectDataTC() {
        new SignupLoginPage(driver).navigate()
                .enterLoginusername("Admin")
                .enterLoginPassword("admin123")
                .clickLoginButton()
                .verifyUserIsLoggedIn();
        new AdminPage(driver).navigate()
                .searchUsername(testData.getJsonData("searchAdminUserError.Username"))
                .selectUserRole(testData.getJsonData("searchAdminUserError.UserRole"))
                .searchStatus(testData.getJsonData("searchAdminUserError.Status"))
                .clickSearchButton()
                .verifyNoRecordsFound(testData.getJsonData("messagesforSearch.NotfoundMessage"));
    }


    //Delet user

    @Description("Verify Delete User")
    @Test(priority = 7)
    public void deletuser() {
        new SignupLoginPage(driver).navigate()
                .enterLoginusername("Admin")
                .enterLoginPassword("admin123")
                .clickLoginButton()
                .verifyUserIsLoggedIn();
        new AdminPage(driver).navigate()
                .selectUserForDeletion()
                .clickDeleteButton()
                .clickConfirmDeleteButton()
                .verifyDeleteSuccessMessage(testData.getJsonData("MessageForDelete.successMessage"));
    }


    //Edit User
    @Description("Verify Edit User")
    @Test(priority = 6)
    public void editUserSuccessfullyTC() {
        new SignupLoginPage(driver).navigate()
                .enterLoginusername("Admin")
                .enterLoginPassword("admin123")
                .clickLoginButton()
                .verifyUserIsLoggedIn();
        new AdminPage(driver).navigate()
                .clickEditButton()
                .editStatus(testData.getJsonData("EditUser.Status"))
                .clickSaveButtonAfterEdit()
                .verifyEditSuccessMessage(testData.getJsonData("EditUser.sucessmessage"));
    }

    //Configurations


    @BeforeClass
    protected void preCondition() {

        testData = new JsonReader("Admin-data");
        //JsonReader testDatalogin = new JsonReader("Login-data");
    }


    @BeforeMethod
    public void setUp() {
        driver = new GUIDriver();


    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quitDriver();
        }
    }
}
