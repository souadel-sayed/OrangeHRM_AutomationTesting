package tests;

import Orange.drivers.GUIDriver;
import Orange.drivers.WebDriverProvider;
import Orange.utils.dataReader.JsonReader;
import org.openqa.selenium.WebDriver;

public class BaseTest implements WebDriverProvider {
    protected GUIDriver driver;
    protected JsonReader testData;


    @Override
    public WebDriver getWebDriver() {
        return driver.get();
    }
}
