package baseTest;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import ui.helpers.DriverHelper;

import static config.ProjectData.driverConfig;
import static ui.helpers.AttachmentHelper.*;

public class BaseTest {
    @BeforeAll
    static void setUp() {
        DriverHelper.configureDriver();
    }

    @AfterEach
    public void addAttachments() {
        if (WebDriverRunner.driver().hasWebDriverStarted()) {
        attachPageSource();
        if (Configuration.browser.equals(Browsers.CHROME))
            attachAsText("Browser console logs", DriverHelper.getConsoleLogs());
        }
    }
}
