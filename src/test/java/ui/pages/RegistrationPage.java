package ui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ui.enums.RegistrationField;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationPage {

    private final String idCssPattern = "#%s";
    private final SelenideElement accountEmailHeader = $(".account");

    @Step("Set '{value}' in the field '{field}'")
    public RegistrationPage fillField(RegistrationField field, String value) {
        $(String.format(idCssPattern, field.getId()))
                .setValue(value);
        return this;
    }

    @Step("The field '{field}' should have error message")
    public RegistrationPage fieldShouldHaveErrorMessage(RegistrationField field, String error) {
        $(String.format("[data-valmsg-for=%s]", field.getId()))
                .shouldHave(text(error));
        return this;
    }

    @Step("Select gender '{field}'")
    public RegistrationPage selectGender(RegistrationField field) {
        $(String.format(idCssPattern, field.getId()))
                .click();
        return this;
    }

    @Step("Click on the button 'Register'")
    public void clickRegister() {
        $("#register-button").click();
    }

    @Step("Registration confirmation should have text 'Your registration completed'")
    public RegistrationPage regConfirmTextShouldBeCorrect() {
        $(".result").shouldHave(text("Your registration completed"));
        return this;
    }

    @Step("Account email in the header should be '{email}'")
    public RegistrationPage headerShouldHaveEmail(String email) {
        accountEmailHeader.shouldHave(text(email));
        return this;
    }

    @Step("Click on the account email in the header")
    public MyAccountPage clickOnAccountEmail() {
        accountEmailHeader.click();
        return new MyAccountPage();
    }
}