package ui.restassured;

import allure.LogFilter;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import ui.endpoints.ApiEndpoint;
import ui.testData.Customer;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiSteps {

    @Step("Add product to shop cart (Api)")
    public static void addProduct(String product, int numberOfProducts, Map<String, String> cookies) {
        given()
                .filter(LogFilter.filters().withCustomTemplates())
                .contentType(ContentType.URLENC)
                .body("addtocart_13.EnteredQuantity=" + numberOfProducts)
                .cookies(cookies)
                .log().uri()
                .log().body()
                .when()
                .post(ApiEndpoint.ADD_TO_CART.addPath(product))
                .then()
                .log().all()
                .statusCode(200);
    }

    @Step("Add billing address (Api)")
    public static void addBillingAddress(Customer customer, Map<String, String> cookies) {
        Map<String, String> details = new LinkedHashMap<>();
        details.put("BillingNewAddress.FirstName", customer.getFirstName());
        details.put("BillingNewAddress.LastName", customer.getLastName());
        details.put("BillingNewAddress.Email", customer.getEmail());
        details.put("BillingNewAddress.CountryId", customer.getCountry().getId());
        details.put("BillingNewAddress.StateProvinceId", "0");
        details.put("BillingNewAddress.City", customer.getCity());
        details.put("BillingNewAddress.Address1", customer.getAddress());
        details.put("BillingNewAddress.ZipPostalCode", customer.getZipCode());
        details.put("BillingNewAddress.PhoneNumber", customer.getPhoneNumber());

        given()
                .filter(LogFilter.filters().withCustomTemplates())
                .contentType(ContentType.URLENC)
                .formParams(details)
                .cookies(cookies)
                .log().uri()
                .log().params()
                .when()
                .post(ApiEndpoint.SAVE_BILLING.getPath())
                .then()
                .log().all()
                .statusCode(200);
    }
}
