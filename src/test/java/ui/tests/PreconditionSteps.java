package ui.tests;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

public class PreconditionSteps {

    @Step("Add product to shop cart")
    public static void addProduct(String product) {
        Selenide.open(product);
        Selenide.$(".add-to-cart-button").click();
    }
}
