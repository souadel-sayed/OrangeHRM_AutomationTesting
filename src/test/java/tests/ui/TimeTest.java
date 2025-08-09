package tests.ui;

import Orange.drivers.GUIDriver;
import Orange.drivers.UITest;
import Orange.pages.SignupLoginPage;
import Orange.pages.TimePage;
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
@Story("Time Page")
@Severity(SeverityLevel.CRITICAL)
@Owner("Souad")
@UITest
public class TimeTest extends BaseTest {

    String timestamp = TimeManager.getSimpleTimestamp();

    @Description("Verify View Employee Timesheet")
    @Test(priority = 1)
    public void viewEmployeeTimesheetSuccessfullyTC() {
        new SignupLoginPage(driver).navigate()
                .enterLoginusername("Admin")
                .enterLoginPassword("admin123")
                .clickLoginButton()
                .verifyUserIsLoggedIn();


        new TimePage(driver).navigate()
                .enterEmployeeName(testData.getJsonData("name"))
                .clickViewButton()
                .verifySuccessfulView(testData.getJsonData("messages.sucess"));

    }

    @Description("Verify View Employee Timesheet with invalid data")
    @Test(priority = 2)
    public void viewEmployeeTimesheetWithInvalidDataTC() {
        new SignupLoginPage(driver).navigate()
                .enterLoginusername("Admin")
                .enterLoginPassword("admin123")
                .clickLoginButton()
                .verifyUserIsLoggedIn();

        new TimePage(driver).navigate()
                .enterEmployeeName(testData.getJsonData("invalidname"))
                .clickViewButton()
                .verifyNotSuccessfulView(testData.getJsonData("messages.error"));
    }


    @BeforeClass
    protected void preCondition() {

        testData = new JsonReader("Time-data");
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
