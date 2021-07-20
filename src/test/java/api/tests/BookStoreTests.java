package api.tests;

import allure.JiraIssue;
import allure.JiraIssues;
import allure.Layer;
import allure.Microservice;
import api.models.Book;
import baseTest.BaseTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Owner("omelyashchik")
@Layer("web")
@Microservice("Registration")
@DisplayName("Verify Book Store")
public class BookStoreTests extends BaseTest {
    private final ApiSteps steps = new ApiSteps();

    @Test
    @JiraIssues({@JiraIssue("QC5-10")})
    @Tags({@Tag("api"), @Tag("positive")})
    @DisplayName("Verify book's isbn")
    public void checkIsbn() {
        List<Book> books = steps.getBooks();

        Allure.step("Check that response contains 8 book", () ->
                assertThat(books.size()).as("Number of books").isEqualTo(8));

        Allure.step("Check that all books have a correct format of 'isbn'", () ->
                books.forEach(book -> {
                    Long isbn = book.getIsbn();
                    Allure.step(String.format("Check that book '%s' has a correct format of 'isbn: %s' (13 digits)",
                            book.getTitle(), isbn), () ->
                            assertThat(isbn).as("isbn").asString().matches("^\\d{13}$"));
                }));
    }

    @Test
    @JiraIssues({@JiraIssue("QC5-10")})
    @Tags({@Tag("api"), @Tag("positive")})
    @DisplayName("Verify that all books can get from Book Store by 'isbn'")
    public void checkBooks() {
        List<Book> books = steps.getBooks();

        Allure.step("Check that all books can get from Book Store by 'isbn' and the information matches", () ->
                books.forEach(expectedBook -> {
                    Long isbn = expectedBook.getIsbn();
                    Allure.step(String.format("Check the book with isbn '%d'", isbn), () -> {
                        Book actualBook = steps.getBook(isbn);
                        assertThat(actualBook).as("Book").isEqualTo(expectedBook);
                    });
                }));
    }
}
