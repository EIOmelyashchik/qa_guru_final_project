package extensions;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import ui.helpers.AttachmentHelper;
import ui.helpers.DriverHelper;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static config.ProjectData.driverConfig;
import static ui.helpers.AttachmentHelper.attachVideo;

public class ActionOnFailure implements AfterEachCallback {

    @Override
    public void afterEach(ExtensionContext context) {
        if (context.getExecutionException().isPresent()) {
            AttachmentHelper.attachScreenshot("Last screenshot");
            if (driverConfig.videoEnabled() && !driverConfig.videoStorage().equals(""))
                attachVideo(DriverHelper.getSessionId());
        }
        closeWebDriver();
    }
}
