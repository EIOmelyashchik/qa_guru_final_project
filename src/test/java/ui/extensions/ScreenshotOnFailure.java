package ui.extensions;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import ui.helpers.AttachmentHelper;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class ScreenshotOnFailure implements AfterEachCallback {

    @Override
    public void afterEach(ExtensionContext context){
        if(context.getExecutionException().isPresent())
            AttachmentHelper.attachScreenshot("Last screenshot");
        closeWebDriver();
    }
}
