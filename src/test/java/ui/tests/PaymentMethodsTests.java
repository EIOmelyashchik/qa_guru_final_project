package ui.tests;

import allure.JiraIssue;
import allure.JiraIssues;
import allure.Layer;
import allure.Microservice;
import com.codeborne.selenide.Selenide;
import extensions.ActionOnFailure;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ui.endpoints.UiEndpoint;
import ui.enums.CheckoutTab;
import ui.enums.CreditCard;
import ui.enums.PaymentMethod;
import ui.pages.CheckoutPage;
import ui.pages.OrderInformationPage;
import ui.testData.Customer;
import ui.testData.GenerateData;

import static io.qameta.allure.Allure.parameter;

@Owner("omelyashchik")
@Layer("web")
@Microservice("Payment")
@DisplayName("Verify payment methods")
@ExtendWith({ActionOnFailure.class})
public class PaymentMethodsTests extends BaseTest {

    @BeforeEach
    void setUpBeforeEach() {
        PreconditionSteps.addProduct("/141-inch-laptop");
    }

    @ParameterizedTest(name = "Verify payment method: {0}")
    @EnumSource(value = PaymentMethod.class, mode = EnumSource.Mode.EXCLUDE, names = "CREDIT_CARD")
    @JiraIssues({@JiraIssue("QC5-10")})
    @Tags({@Tag("web"), @Tag("positive")})
    void checkPaymentMethod(PaymentMethod paymentMethod) {
        parameter("payment method", paymentMethod.getName());

        CheckoutPage checkoutPage = Selenide.open(UiEndpoint.CHECKOUT.getPath(), CheckoutPage.class);
        Customer customer = GenerateData.getCustomer();
        String totalCost = checkoutPage.fillBillingData(customer)
                .goToPaymentMethodTab()
                .selectPaymentMethod(paymentMethod)
                .clickContinue(CheckoutTab.PAYMENT_METHOD)
                .clickContinue(CheckoutTab.PAYMENT_INFORMATION)
                .getTotalCost();

        OrderInformationPage orderInformationPage = checkoutPage.clickConfirm()
                .openOrderDetails();
        orderInformationPage.totalShouldBeCorrect(totalCost)
                .billingAddressShouldBeCorrect(customer)
                .shippingAddressShouldBeCorrect(customer)
                .paymentMethodShouldBeCorrect(paymentMethod);
    }

    @ParameterizedTest(name = "Verify payment by credit card: {0}")
    @EnumSource(value = CreditCard.class)
    @JiraIssues({@JiraIssue("QC5-10")})
    @Tags({@Tag("web"), @Tag("positive")})
    void checkCashOnDelivery(CreditCard creditCard) {
        parameter("Credit card", creditCard.getType());
        parameter("Number of credit card", creditCard.getCardNumber());

        CheckoutPage checkoutPage = Selenide.open(UiEndpoint.CHECKOUT.getPath(), CheckoutPage.class);
        Customer customer = GenerateData.getCustomer();
        String totalCost = checkoutPage.fillBillingData(customer)
                .goToPaymentMethodTab()
                .selectPaymentMethod(PaymentMethod.CREDIT_CARD)
                .clickContinue(CheckoutTab.PAYMENT_METHOD)
                .fillCreditCardData(creditCard)
                .clickContinue(CheckoutTab.PAYMENT_INFORMATION)
                .getTotalCost();

        OrderInformationPage orderInformationPage = checkoutPage.clickConfirm()
                .openOrderDetails();
        orderInformationPage.totalShouldBeCorrect(totalCost)
                .billingAddressShouldBeCorrect(customer)
                .shippingAddressShouldBeCorrect(customer)
                .paymentMethodShouldBeCorrect(PaymentMethod.CREDIT_CARD);
    }
}
