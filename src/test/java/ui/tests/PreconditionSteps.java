package ui.tests;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class PreconditionSteps {

    @Step("Add product to shop cart")
    public static void addProduct(String product) {
        Selenide.open(product);
        $(".add-to-cart-button").click();
        $("#topcartlink").click();
        $("#checkout").click();
        $(".checkout-as-guest-button").click();
    }
}
