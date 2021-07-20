package ui.cookie;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;
import ui.endpoints.UiEndpoint;

import java.util.Map;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class CookiesManger {

    private final Map<String, String> cookies;

    public CookiesManger(Map<String, String> cookies) {
        this.cookies = cookies;
    }

    public Map<String, String> getCookies() {
        return cookies;
    }

    @Step("Add cookies to browser session")
    public void setCookiesWebDriver() {
        Selenide.open(UiEndpoint.FAVICON.getPath());
        cookies.forEach((k, v) -> getWebDriver().manage().addCookie(new Cookie(k, v)));
    }
}
