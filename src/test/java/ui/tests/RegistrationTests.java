package ui.tests;

import allure.JiraIssue;
import allure.JiraIssues;
import allure.Layer;
import allure.Microservice;
import baseTest.BaseTest;
import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ui.testData.GenerateData;
import ui.testData.User;
import ui.endpoints.UiEndpoint;
import ui.enums.RegistrationField;
import extensions.ActionOnFailure;
import ui.pages.MyAccountPage;
import ui.pages.RegistrationPage;

import static io.qameta.allure.Allure.parameter;

@Owner("omelyashchik")
@Layer("web")
@Microservice("Registration")
@DisplayName("Verify registration")
@ExtendWith({ActionOnFailure.class})
public class RegistrationTests extends BaseTest {
    private RegistrationPage registrationPage;
    private Faker faker;
    private User user;

    @BeforeEach
    @DisplayName("Open registration page")
    void setUpBeforeEach() {
        registrationPage = Selenide.open(UiEndpoint.REGISTER.getPath(), RegistrationPage.class);
    }

    @BeforeEach
    void generateData() {
        faker = new Faker();
        user = GenerateData.getUser();
    }

    @Test
    @JiraIssues({@JiraIssue("QC5-10")})
    @Tags({@Tag("web"), @Tag("positive")})
    @DisplayName("Successful registration")
    void checkSuccessfulRegistration() {
        registrationPage
                .selectGender(RegistrationField.GENDER_FEMALE)
                .fillField(RegistrationField.FIRST_NAME, user.getFirstName())
                .fillField(RegistrationField.LAST_NAME, user.getLastName())
                .fillField(RegistrationField.EMAIL, user.getEmail())
                .fillField(RegistrationField.PASSWORD, user.getPassword())
                .fillField(RegistrationField.CONFIRM_PASSWORD, user.getPassword())
                .clickRegister();

        registrationPage
                .regConfirmTextShouldBeCorrect()
                .headerShouldHaveEmail(user.getEmail());

        MyAccountPage myAccountPage = registrationPage.clickOnAccountEmail();

        myAccountPage
                .genderShouldBeSelected(RegistrationField.GENDER_FEMALE)
                .fieldShouldHaveValue(RegistrationField.FIRST_NAME, user.getFirstName())
                .fieldShouldHaveValue(RegistrationField.LAST_NAME, user.getLastName())
                .fieldShouldHaveValue(RegistrationField.EMAIL, user.getEmail());
    }

    @Test
    @JiraIssues({@JiraIssue("QC5-10")})
    @Tags({@Tag("web"), @Tag("negative")})
    @DisplayName("Unsuccessful registration (don't fill all required fields)")
    void checkUnsuccessfulRegistrationOne() {
        registrationPage.clickRegister();

        registrationPage
                .fieldShouldHaveErrorMessage(RegistrationField.FIRST_NAME, generateError(RegistrationField.FIRST_NAME))
                .fieldShouldHaveErrorMessage(RegistrationField.LAST_NAME, generateError(RegistrationField.LAST_NAME))
                .fieldShouldHaveErrorMessage(RegistrationField.EMAIL, generateError(RegistrationField.EMAIL))
                .fieldShouldHaveErrorMessage(RegistrationField.PASSWORD, generateError(RegistrationField.PASSWORD))
                .fieldShouldHaveErrorMessage(RegistrationField.CONFIRM_PASSWORD, generateError(RegistrationField.PASSWORD));
    }

    @Test
    @JiraIssues({@JiraIssue("QC5-10")})
    @Tags({@Tag("web"), @Tag("negative")})
    @DisplayName("Unsuccessful registration (don't fill first name)")
    void checkUnsuccessfulRegistrationTwo() {
        registrationPage
                .fillField(RegistrationField.LAST_NAME, user.getLastName())
                .fillField(RegistrationField.EMAIL, user.getEmail())
                .fillField(RegistrationField.PASSWORD, user.getPassword())
                .fillField(RegistrationField.CONFIRM_PASSWORD, user.getPassword())
                .clickRegister();

        registrationPage
                .fieldShouldHaveErrorMessage(RegistrationField.FIRST_NAME, generateError(RegistrationField.FIRST_NAME));
    }

    @Test
    @JiraIssues({@JiraIssue("QC5-10")})
    @Tags({@Tag("web"), @Tag("negative")})
    @DisplayName("Unsuccessful registration (don't fill last name)")
    void checkUnsuccessfulRegistrationThree() {
        registrationPage
                .fillField(RegistrationField.FIRST_NAME, user.getFirstName())
                .fillField(RegistrationField.EMAIL, user.getEmail())
                .fillField(RegistrationField.PASSWORD, user.getPassword())
                .fillField(RegistrationField.CONFIRM_PASSWORD, user.getPassword())
                .clickRegister();

        registrationPage
                .fieldShouldHaveErrorMessage(RegistrationField.LAST_NAME, generateError(RegistrationField.LAST_NAME));
    }

    @Test
    @JiraIssues({@JiraIssue("QC5-10")})
    @Tags({@Tag("web"), @Tag("negative")})
    @DisplayName("Unsuccessful registration (don't fill user.getEmail())")
    void checkUnsuccessfulRegistrationFour() {
        registrationPage
                .fillField(RegistrationField.FIRST_NAME, user.getFirstName())
                .fillField(RegistrationField.LAST_NAME, user.getLastName())
                .fillField(RegistrationField.PASSWORD, user.getPassword())
                .fillField(RegistrationField.CONFIRM_PASSWORD, user.getPassword())
                .clickRegister();

        registrationPage
                .fieldShouldHaveErrorMessage(RegistrationField.EMAIL, generateError(RegistrationField.EMAIL));
    }

    @Test
    @JiraIssues({@JiraIssue("QC5-10")})
    @Tags({@Tag("web"), @Tag("negative")})
    @DisplayName("Unsuccessful registration (don't fill password)")
    void checkUnsuccessfulRegistrationFive() {
        registrationPage
                .fillField(RegistrationField.FIRST_NAME, user.getFirstName())
                .fillField(RegistrationField.LAST_NAME, user.getLastName())
                .fillField(RegistrationField.EMAIL, user.getEmail())
                .fillField(RegistrationField.CONFIRM_PASSWORD, user.getPassword())
                .clickRegister();

        registrationPage
                .fieldShouldHaveErrorMessage(RegistrationField.PASSWORD, generateError(RegistrationField.PASSWORD));
    }

    @Test
    @JiraIssues({@JiraIssue("QC5-10")})
    @Tags({@Tag("web"), @Tag("negative")})
    @DisplayName("Unsuccessful registration (don't confirm password)")
    void checkUnsuccessfulRegistrationSix() {
        registrationPage
                .fillField(RegistrationField.FIRST_NAME, user.getFirstName())
                .fillField(RegistrationField.LAST_NAME, user.getLastName())
                .fillField(RegistrationField.EMAIL, user.getEmail())
                .fillField(RegistrationField.PASSWORD, user.getPassword())
                .clickRegister();

        registrationPage
                .fieldShouldHaveErrorMessage(RegistrationField.CONFIRM_PASSWORD, generateError(RegistrationField.PASSWORD));
    }

    @ParameterizedTest(name = "Unsuccessful registration (fill wrong email: {0})")
    @ValueSource(strings = {"qwe", "123@", "123.com", "123@.com", "123@mail.11", "@mail.com"})
    @JiraIssues({@JiraIssue("QC5-10")})
    @Tags({@Tag("web"), @Tag("negative")})
    void checkUnsuccessfulRegistrationSeven(String wrongEmail) {
        parameter("Wrong user.getEmail()", wrongEmail);
        registrationPage
                .fillField(RegistrationField.FIRST_NAME, user.getFirstName())
                .fillField(RegistrationField.LAST_NAME, user.getLastName())
                .fillField(RegistrationField.EMAIL, wrongEmail)
                .fillField(RegistrationField.PASSWORD, user.getPassword())
                .fillField(RegistrationField.CONFIRM_PASSWORD, user.getPassword())
                .clickRegister();

        final String expectedErrorMessage = "Wrong email";
        registrationPage
                .fieldShouldHaveErrorMessage(RegistrationField.EMAIL, expectedErrorMessage);
    }

    @Test
    @JiraIssues({@JiraIssue("QC5-10")})
    @Tags({@Tag("web"), @Tag("negative")})
    @DisplayName("Unsuccessful registration (fill short password)")
    void checkUnsuccessfulRegistrationEight() {
        String shortPassword = faker.internet().password(1, 5);
        registrationPage
                .fillField(RegistrationField.FIRST_NAME, user.getFirstName())
                .fillField(RegistrationField.LAST_NAME, user.getLastName())
                .fillField(RegistrationField.EMAIL, user.getEmail())
                .fillField(RegistrationField.PASSWORD, shortPassword)
                .fillField(RegistrationField.CONFIRM_PASSWORD, shortPassword)
                .clickRegister();

        final String expectedErrorMessage = "The password should have at least 6 characters.";
        registrationPage
                .fieldShouldHaveErrorMessage(RegistrationField.PASSWORD, expectedErrorMessage);
    }

    @Test
    @JiraIssues({@JiraIssue("QC5-10")})
    @Tags({@Tag("web"), @Tag("negative")})
    @DisplayName("Unsuccessful registration (no match passwords)")
    void checkUnsuccessfulRegistrationNine() {
        String anotherPassword = faker.internet().password();
        registrationPage
                .fillField(RegistrationField.FIRST_NAME, user.getFirstName())
                .fillField(RegistrationField.LAST_NAME, user.getLastName())
                .fillField(RegistrationField.EMAIL, user.getEmail())
                .fillField(RegistrationField.PASSWORD, user.getPassword())
                .fillField(RegistrationField.CONFIRM_PASSWORD, anotherPassword)
                .clickRegister();

        final String expectedErrorMessage = "The password and confirmation password do not match.";
        registrationPage
                .fieldShouldHaveErrorMessage(RegistrationField.CONFIRM_PASSWORD, expectedErrorMessage);
    }

    private String generateError(RegistrationField field) {
        return String.format("%s is required", field.getName());
    }
}
