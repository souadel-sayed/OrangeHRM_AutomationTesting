package Orange.drivers;


import Orange.utils.dataReader.PropertyReader;
import Orange.utils.logs.LogsManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class EdgeFactory extends AbstractDriver {
    private EdgeOptions getOptions() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        options.addArguments("--start-maximized");
        Map<String, Object> prefs = new HashMap<>();
        String userDir = System.getProperty("user.dir");
        String downloadPath = userDir + "\\src\\test\\resources\\downloads";
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.default_directory", downloadPath);
        options.setExperimentalOption("prefs", prefs);
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        options.setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
        options.setCapability(CapabilityType.ENABLE_DOWNLOADS, true);
        options.setAcceptInsecureCerts(true);
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        options.addExtensions(haramBlurExtension);
        switch (PropertyReader.getProperty("executionType")) {
            case "LocalHeadless" -> options.addArguments("--headless=new");
            case "Remote" -> {
                options.addArguments("--disable-gpu");
                options.addArguments("--disable-extensions");
                options.addArguments("--headless=new");
            }
        }
        return options;
    }

    @Override
    public WebDriver createDriver() {
        if (PropertyReader.getProperty("executionType").equalsIgnoreCase("Local") ||
                PropertyReader.getProperty("executionType").equalsIgnoreCase("LocalHeadless")) {
            return new EdgeDriver(getOptions());
        } else if (PropertyReader.getProperty("executionType").equalsIgnoreCase("Remote")) {
            try {
                return new RemoteWebDriver(
                        new URI("http://" + remoteHost + ":" + remotePort + "/wd/hub").toURL(), getOptions()
                );
            } catch (Exception e) {
                LogsManager.error("Error creating RemoteWebDriver: " + e.getMessage());
                throw new RuntimeException("Failed to create RemoteWebDriver", e);
            }

        } else {
            LogsManager.error("Invalid execution type: " + PropertyReader.getProperty("executionType"));
            throw new IllegalArgumentException("Invalid execution type: " + PropertyReader.getProperty("executionType"));
        }
    }
}
