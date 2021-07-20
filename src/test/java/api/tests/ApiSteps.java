package api.tests;

import api.endpoints.ApiEndpoint;
import api.models.Book;
import api.models.BookStore;
import api.models.NewUser;
import api.models.User;
import api.spec.Specs;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiSteps {

    @Step("Send POST request: endpoint - {endpoint}")
    public Response post(Object object, String endpoint, int statusCode) {
        return given(Specs.accountRequest)
                .body(object)
                .log().body()
                .when()
                .post(endpoint)
                .then()
                .log().all()
                .statusCode(statusCode)
                .extract().response();
    }

    @Step("Create user with incorrect password: {user}")
    public String createUserWithIncorrectPassword(NewUser user) {
        return post(user, ApiEndpoint.USER.getPath(), 400)
                .then()
                .extract().path("message");
    }

    @Step("Create user: {user}")
    public User createUser(NewUser user) {
        return post(user, ApiEndpoint.USER.getPath(), 201)
                .then()
                .extract().as(User.class);
    }

    @Step("Get response with all books from the book store")
    public Response getResponseWithBooks() {
        return given(Specs.bookStoreRequest)
                .when()
                .get(ApiEndpoint.BOOKS.getPath())
                .then()
                .spec(Specs.responseSpec)
                .log().body()
                .extract().response();
    }

    @Step("Get all books from the book store")
    public List<Book> getBooks() {
        return getResponseWithBooks()
                .then()
                .extract().as(BookStore.class)
                .getBooks();
    }

    @Step("Get book from the book store by isbn {isbn}")
    public Book getBook(Long isbn) {
        return given(Specs.bookStoreRequest)
                .when()
                .queryParam("ISBN", isbn)
                .get(ApiEndpoint.BOOK.getPath())
                .then()
                .spec(Specs.responseSpec)
                .log().body()
                .extract().as(Book.class);
    }
}
