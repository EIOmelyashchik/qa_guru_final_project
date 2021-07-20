package ui.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import ui.enums.RegistrationField;

import static com.codeborne.selenide.Selenide.$;

public class MyAccountPage {

    private final String idCssPattern = "#%s";

    @Step("Field '{field}' should have the value '{value}'")
    public MyAccountPage fieldShouldHaveValue(RegistrationField field, String value) {
        $(String.format(idCssPattern, field.getId()))
                .shouldHave(Condition.value(value));
        return this;
    }

    @Step("Gender '{field}' should be selected")
    public MyAccountPage genderShouldBeSelected(RegistrationField field) {
        $(String.format(idCssPattern, field.getId()))
                .shouldBe(Condition.checked);
        return this;
    }
}