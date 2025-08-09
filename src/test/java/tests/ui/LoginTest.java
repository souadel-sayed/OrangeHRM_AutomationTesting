package tests.ui;

import Orange.drivers.GUIDriver;
import Orange.drivers.UITest;
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
@Story("User Login")
@Severity(SeverityLevel.CRITICAL)
@Owner("Souad")
@UITest
public class LoginTest extends BaseTest {

    String timestamp = TimeManager.getSimpleTimestamp();

    @Description("Verify user can login with valid credentials")
    @Test(priority = 1)
    public void validLoginTC() {

        new SignupLoginPage(driver).navigate()
                .enterLoginusername(testData.getJsonData("name"))
                .enterLoginPassword(testData.getJsonData("password"))
                .clickLoginButton()
                .verifyUserIsLoggedIn();

    }

    @Description("Verify user cannot login with invalid Password")
    @Test(priority = 2)
    public void inValidLoginUsingInvalidPasswordTC() {

        new SignupLoginPage(driver).navigate()
                .enterLoginusername(testData.getJsonData("name"))
                .enterLoginPassword(testData.getJsonData("invalidpassword"))
                .clickLoginButton()
                .verifyLoginErrorMsg(testData.getJsonData("messages.error"));
    }

    //Configurations
    @BeforeClass
    protected void preCondition() {
        testData = new JsonReader("login-data");
    }

    @BeforeMethod
    public void setUp() {
        driver = new GUIDriver();

    }

    @AfterMethod
    public void tearDown() {
        driver.quitDriver();
    }
}
