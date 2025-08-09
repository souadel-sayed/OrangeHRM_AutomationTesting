package tests.ui;

import Orange.drivers.GUIDriver;
import Orange.drivers.UITest;
import Orange.pages.PIMPage;
import Orange.pages.SignupLoginPage;
import Orange.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.BaseTest;

@Epic("OrangeHRM Automation")
@Feature("UI User Management")
@Story("Pim Page")
@Severity(SeverityLevel.CRITICAL)
@Owner("Souad")
@UITest
public class PIMTest extends BaseTest {

    @Description("Verify Search Employee by valid data")
    @Test(priority = 1)
    public void searchEmployeeByValidDataTC() {

        new SignupLoginPage(driver).navigate()
                .enterLoginusername("Admin")
                .enterLoginPassword("admin123")
                .clickLoginButton()
                .verifyUserIsLoggedIn();

        new PIMPage(driver).navigate()
                .searchEmployeeByName(testData.getJsonData("searchForEmployee.valid.Name"))
                .searchEmployeeById(testData.getJsonData("searchForEmployee.valid.employeeId"))
                .clickSearchButton()
                .FoundMess(testData.getJsonData("messages.success"));

    }


    @Description("Verify Add Employee with valid data")
    @Test(priority = 2)
    public void addEmployeeWithValidDataTC() {
        new SignupLoginPage(driver).navigate()
                .enterLoginusername("Admin")
                .enterLoginPassword("admin123")
                .clickLoginButton()
                .verifyUserIsLoggedIn();

        new PIMPage(driver).navigate()
                .clickAddButton()
                .enterEmployeeFirstName(testData.getJsonData("Employee-credentials.valid.firstName"))
                .enterEmployeeLastName(testData.getJsonData("Employee-credentials.valid.lastName"))
                .enterEmployeeMiddleName(testData.getJsonData("Employee-credentials.valid.middleName"))
                .enterEmployeeId(testData.getJsonData("addEmployee.valid.employeeId"))
                .clickSaveButton()
                .verifySuccessMessage(testData.getJsonData("messages.sucesssADD"));

    }

    @Description("Verify Add Employee with incomplete data")
    @Test(priority = 3)
    public void addEmployeeWithIncompleteDataTC() {
        new SignupLoginPage(driver).navigate()
                .enterLoginusername("Admin")
                .enterLoginPassword("admin123")
                .clickLoginButton()
                .verifyUserIsLoggedIn();

        new PIMPage(driver).navigate()
                .clickAddButton()
                .enterEmployeeFirstName(testData.getJsonData("Employee-credentials.invalid.firstName"))
                .enterEmployeeMiddleName(testData.getJsonData("Employee-credentials.invalid.lastName"))
                .enterEmployeeId(testData.getJsonData("addEmployee.invalid.employeeId"))
                .clickSaveButton()
                .verifyUserNameExistsreq(testData.getJsonData("messages.Required"));

    }

    @Description("Verify Add Employee with Exist ID")
    @Test(priority = 4)
    public void addEmployeeWithExistid() {
        new SignupLoginPage(driver).navigate()
                .enterLoginusername("Admin")
                .enterLoginPassword("admin123")
                .clickLoginButton()
                .verifyUserIsLoggedIn();

        new PIMPage(driver).navigate()
                .clickAddButton()
                .enterEmployeeFirstName(testData.getJsonData("Employee-credentials.invalid.firstName"))
                .enterEmployeeMiddleName(testData.getJsonData("Employee-credentials.invalid.lastName"))
                .enterEmployeeLastName(testData.getJsonData("Employee-credentials.valid.lastName"))
                .enterEmployeeId(testData.getJsonData("addEmployee.invalid.exixtID"))
                .clickSaveButton()
                .verifyEmployeeIdExistsMessage(testData.getJsonData("messages.invalidID"));

    }


    @BeforeClass
    protected void preCondition() {

        testData = new JsonReader("PIM-data");
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
