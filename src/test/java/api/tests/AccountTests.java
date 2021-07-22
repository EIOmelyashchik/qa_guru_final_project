package api.tests;

import allure.JiraIssue;
import allure.JiraIssues;
import allure.Layer;
import allure.Microservice;
import api.models.NewUser;
import api.models.User;
import api.testData.GenerateData;
import baseTest.BaseTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.qameta.allure.Allure.parameter;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Owner("omelyashchik")
@Layer("api")
@Microservice("Registration")
@DisplayName("Verify Account")
public class AccountTests extends BaseTest {
    private final ApiSteps steps = new ApiSteps();

    @Test
    @JiraIssues({@JiraIssue("QC5-10")})
    @Tags({@Tag("api"), @Tag("positive")})
    @DisplayName("Verify creation account")
    public void createAccount() {
        NewUser newUser = GenerateData.getUser(8, 16, true, true, true);
        User user = steps.createUser(newUser);

        Allure.step(String.format("Check that userID '%s' has a correct format", user.getUserID()), () ->
                assertThat(user.getUserID()).as("userID").asString().matches("^\\w{8}-\\w{4}-\\w{4}-\\w{4}-\\w{12}$"));
        Allure.step(String.format("Check that response contains correct username: '%s'", user.getUsername()), () ->
                assertThat(user.getUsername()).as("Username").isEqualTo(newUser.getUserName()));
        Allure.step("Check that response doesn't contain books", () ->
                assertThat(user.getBooks().size()).as("Books amount").isEqualTo(0));
    }

    static Stream<Arguments> incorrectPasswords() {
        return Stream.of(
                //short password
                Arguments.of(GenerateData.getUser(1, 7, true, true, true)),
                //without uppercase
                Arguments.of(GenerateData.getUser(8, 16, false, true, true)),
                //without spec symbols
                Arguments.of(GenerateData.getUser(8, 16, true, false, true)),
                //without digits
                Arguments.of(GenerateData.getUser(8, 16, true, true, false)));
    }

    @JiraIssues({@JiraIssue("QC5-10")})
    @Tags({@Tag("api"), @Tag("negative")})
    @ParameterizedTest(name = "Verify unsuccessful creation account with incorrect password: {0}")
    @MethodSource("incorrectPasswords")
    public void createAccountIncorrectPassword(NewUser newUser) {
        parameter("Password", newUser.getPassword());

        String actualErrorMessage = steps.createUserWithIncorrectPassword(newUser);
        String expectedErrorMessage = "Passwords must have at least one non alphanumeric character, one digit ('0'-'9'), " +
                "one uppercase ('A'-'Z'), one lowercase ('a'-'z'), one special character and Password must be eight " +
                "characters or longer.";
        Allure.step("Check that response contains error message", () ->
                assertThat(actualErrorMessage).as("Error message").isEqualTo(expectedErrorMessage));
    }
}
