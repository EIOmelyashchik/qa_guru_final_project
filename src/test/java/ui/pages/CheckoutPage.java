package ui.pages;

import io.qameta.allure.Step;
import ui.enums.CheckoutTab;
import ui.enums.CreditCard;
import ui.enums.PaymentMethod;
import ui.testData.Customer;

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

    @Step("Fill billing data: {customer}")
    public CheckoutPage fillBillingData(Customer customer) {
        $("#BillingNewAddress_FirstName").setValue(customer.getFirstName());
        $("#BillingNewAddress_LastName").setValue(customer.getLastName());
        $("#BillingNewAddress_Email").setValue(customer.getEmail());
        $("#BillingNewAddress_CountryId").selectOption(customer.getCountry().getName());
        $("#BillingNewAddress_City").setValue(customer.getCity());
        $("#BillingNewAddress_Address1").setValue(customer.getAddress());
        $("#BillingNewAddress_ZipPostalCode").setValue(customer.getZipCode());
        $("#BillingNewAddress_PhoneNumber").setValue(customer.getPhoneNumber());
        return this;
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