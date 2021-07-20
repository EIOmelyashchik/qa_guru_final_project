package ui.pages;

import io.qameta.allure.Step;
import ui.enums.PaymentMethod;
import ui.testData.Customer;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class OrderInformationPage {

    @Step("'Order Total' should be {orderTotal}")
    public OrderInformationPage totalShouldBeCorrect(String orderTotal) {
        $(".order-total strong").shouldHave(text(orderTotal));
        return this;
    }

    @Step("'Billing Address' should have correct data")
    public OrderInformationPage billingAddressShouldBeCorrect(Customer customer) {
        $(".billing-info")
                .shouldHave(text(customer.getFirstName()))
                .shouldHave(text(customer.getLastName()))
                .shouldHave(text(customer.getEmail()))
                .shouldHave(text(customer.getCountry().getName()))
                .shouldHave(text(customer.getCity()))
                .shouldHave(text(customer.getAddress()))
                .shouldHave(text(customer.getZipCode()))
                .shouldHave(text(customer.getPhoneNumber()));
        return this;
    }

    @Step("'Shipping Address' should have correct data")
    public OrderInformationPage shippingAddressShouldBeCorrect(Customer customer) {
        $(".shipping-info")
                .shouldHave(text(customer.getFirstName()))
                .shouldHave(text(customer.getLastName()))
                .shouldHave(text(customer.getEmail()))
                .shouldHave(text(customer.getCountry().getName()))
                .shouldHave(text(customer.getCity()))
                .shouldHave(text(customer.getAddress()))
                .shouldHave(text(customer.getZipCode()))
                .shouldHave(text(customer.getPhoneNumber()));
        return this;
    }

    @Step("'Payment Method' should be {paymentMethod}")
    public OrderInformationPage paymentMethodShouldBeCorrect(PaymentMethod paymentMethod) {
        $(".payment-method").shouldHave(text(paymentMethod.getName()));
        return this;
    }
}
