package api.spec;

import allure.LogFilter;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import config.ProjectData;

import static config.ProjectData.testDataConfig;
import static io.restassured.RestAssured.with;

public class Specs {
    public static RequestSpecification bookStoreRequest = with()
            .filter(LogFilter.filters().withCustomTemplates())
            .baseUri(testDataConfig.apiUrl())
            .basePath("/BookStore/v1")
            .log().uri()
            .contentType(ContentType.JSON);

    public static RequestSpecification accountRequest = with()
            .filter(LogFilter.filters().withCustomTemplates())
            .baseUri(testDataConfig.apiUrl())
            .basePath("/Account/v1")
            .log().uri()
            .contentType(ContentType.JSON);

    public static ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();
}
