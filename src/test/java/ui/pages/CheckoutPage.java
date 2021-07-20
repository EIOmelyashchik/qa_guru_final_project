package ui.pages;

import io.qameta.allure.Step;
import ui.enums.CheckoutTab;
import ui.enums.CreditCard;
import ui.enums.PaymentMethod;

import static com.codeborne.selenide.Selenide.$;

public class CheckoutPage {

    private final String idCssPattern = "#%s";

    @Step("Click on the button 'Continue'")
    public CheckoutPage clickContinue(CheckoutTab tab) {
        $(String.format(idCssPattern, tab.getId()))
                .$("[value=Continue]").click();
        return this;
    }

    @Step("Click on the button 'Confirm'")
    public CheckoutPage clickConfirm() {
        $("[value=Confirm]").click();
        return this;
    }

    @Step("Go to 'Payment Method' tab'")
    public CheckoutPage goToPaymentMethodTab() {
        return clickContinue(CheckoutTab.BILLING_ADDRESS)
                .clickContinue(CheckoutTab.SHIPPING_ADDRESS)
                .clickContinue(CheckoutTab.SHIPPING_METHOD);
    }

    @Step("Select '{paymentMethod}' Payment Method")
    public CheckoutPage selectPaymentMethod(PaymentMethod paymentMethod) {
        $(String.format(idCssPattern, paymentMethod.getId())).click();
        return this;
    }

    @Step("Get total cost")
    public String getTotalCost() {
        return $("tr:last-of-type .cart-total-right").getText();
    }

    @Step("Click on the link 'Click here for order details.'")
    public OrderInformationPage openOrderDetails() {
        $("[href*='orderdetails']").click();
        return new OrderInformationPage();
    }

    @Step("Fill card data: {creditCard}")
    public CheckoutPage fillCreditCardData(CreditCard creditCard) {
        $("#CreditCardType").selectOption(creditCard.getType());
        $("#CardholderName").setValue(creditCard.getCardHolder());
        $("#CardNumber").setValue(creditCard.getCardNumber());
        $("#ExpireMonth").selectOption(creditCard.getExpirationDate().split("/")[0]);
        $("#ExpireYear").selectOption(creditCard.getExpirationDate().split("/")[1]);
        $("#CardCode").setValue(creditCard.getCode());
        return this;
    }
}