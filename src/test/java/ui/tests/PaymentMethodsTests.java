package ui.tests;

import allure.JiraIssue;
import allure.JiraIssues;
import allure.Layer;
import allure.Microservice;
import baseTest.BaseTest;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import config.ProjectData;
import ui.cookie.CookiesManger;
import ui.endpoints.UiEndpoint;
import ui.enums.CheckoutTab;
import ui.enums.CreditCard;
import ui.enums.PaymentMethod;
import ui.extensions.ScreenshotOnFailure;
import ui.pages.CheckoutPage;
import ui.pages.OrderInformationPage;
import ui.restassured.AuthApi;
import ui.restassured.ApiSteps;
import ui.testData.Customer;
import ui.testData.GenerateData;

import static io.qameta.allure.Allure.parameter;

@Owner("omelyashchik")
@Layer("web")
@Microservice("Payment")
@DisplayName("Verify payment methods")
@ExtendWith({ScreenshotOnFailure.class})
public class PaymentMethodsTests extends BaseTest {
    private CookiesManger cookiesManger;
    private Customer customer;

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = ProjectData.testDataConfig.webUrl();
    }

    @BeforeEach
    void setUpBeforeEach() {
        cookiesManger = new CookiesManger(new AuthApi().cookiesWithoutLogIn());
        customer = GenerateData.getCustomer();
        ApiSteps.addProduct("/details/13/1", 1, cookiesManger.getCookies());
        ApiSteps.addBillingAddress(customer, cookiesManger.getCookies());
    }

    @ParameterizedTest(name = "Verify payment method: {0}")
    @EnumSource(value = PaymentMethod.class, mode = EnumSource.Mode.EXCLUDE, names = "CREDIT_CARD")
    @JiraIssues({@JiraIssue("QC5-10")})
    @Tags({@Tag("web"), @Tag("api"), @Tag("positive")})
    void checkPaymentMethod(PaymentMethod paymentMethod) {
        parameter("payment method", paymentMethod.getName());
        cookiesManger.setCookiesWebDriver();

        CheckoutPage checkoutPage = Selenide.open(UiEndpoint.CHECKOUT.getPath(), CheckoutPage.class);
        String totalCost = checkoutPage.goToPaymentMethodTab()
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
    @Tags({@Tag("web"), @Tag("api"), @Tag("positive")})
    void checkCashOnDelivery(CreditCard creditCard) {
        parameter("Credit card", creditCard.getType());
        parameter("Number of credit card", creditCard.getCardNumber());
        cookiesManger.setCookiesWebDriver();

        CheckoutPage checkoutPage = Selenide.open(UiEndpoint.CHECKOUT.getPath(), CheckoutPage.class);
        String totalCost = checkoutPage.goToPaymentMethodTab()
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
