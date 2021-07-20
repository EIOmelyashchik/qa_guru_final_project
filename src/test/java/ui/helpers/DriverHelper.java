package ui.helpers;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import config.ProjectData;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static org.openqa.selenium.logging.LogType.BROWSER;
import static config.ProjectData.driverConfig;

public class DriverHelper {

    public static String getWebRemoteDriver() {
        return String.format(driverConfig.webRemoteDriverUrl(),
                driverConfig.webRemoteDriverUser(),
                driverConfig.webRemoteDriverPassword());
    }

    public static boolean isRemoteWebDriver() {
        return !driverConfig.webRemoteDriverUrl().equals("");
    }

    public static String getSessionId() {
        return ((RemoteWebDriver) getWebDriver()).getSessionId().toString().replace("selenoid", "");
    }

    public static String getConsoleLogs() {
        return String.join("\n", Selenide.getWebDriverLogs(BROWSER));
    }

    public static void configureDriver() {
        addListener("AllureSelenide", new AllureSelenide());

        Configuration.baseUrl = ProjectData.testDataConfig.webUrl();
        Configuration.browser = driverConfig.webBrowser();
        Configuration.browserVersion = driverConfig.webBrowserVersion();
        Configuration.browserSize = driverConfig.webBrowserSize();

        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (isRemoteWebDriver()) {
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
            Configuration.remote = getWebRemoteDriver();
        }

        Configuration.browserCapabilities = capabilities;
    }
}
