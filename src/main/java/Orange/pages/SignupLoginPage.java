package Orange.pages;


import Orange.drivers.GUIDriver;
import Orange.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class SignupLoginPage {
    private final String signupLoginEndpoint = "/auth/login";
    private final String verfiyLoginEndpoint = "/dashboard/index";

    //locators
    private final By loginsernam = By.cssSelector("input[placeholder='Username']");
    private final By loginPassword = By.cssSelector("input[placeholder='Password']");
    private final By loginButton = By.cssSelector("button[type='submit']");
    private final By loginError = By.cssSelector(".oxd-text.oxd-text--p.oxd-alert-content-text");

    private GUIDriver driver;

    public SignupLoginPage(GUIDriver driver) {
        this.driver = driver;
    }

    //actions
    @Step("Navigate to Login Page")
    public SignupLoginPage navigate() {
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb") + signupLoginEndpoint);
        return this;
    }

    @Step("Enter name {username} in login field")
    public SignupLoginPage enterLoginusername(String username) {
        driver.element().type(loginsernam, username);
        return this;
    }

    @Step("Enter password {password} in login field")
    public SignupLoginPage enterLoginPassword(String password) {
        driver.element().type(loginPassword, password);
        return this;
    }

    @Step("Click login button")
    public SignupLoginPage clickLoginButton() {
        driver.element().click(loginButton);
        return this;
    }


    @Step("Verify that the user is logged in")

    public SignupLoginPage verifyUserIsLoggedIn() {
        driver.verification().assertPageUrl(PropertyReader.getProperty("baseUrlWeb") + verfiyLoginEndpoint);
        return this;
    }


    @Step("Assert that the login failed")
    public SignupLoginPage verifyLoginErrorMsg(String errorExpected) {
        String errorActual = driver.element().getText(loginError);
        driver.verification().Equals(errorActual, errorExpected, "Register error message is  as expected");
        System.out.println("Login error message is as expected: " + errorActual);

        return this;
    }

}
