package ui.enums;

public enum RegistrationField {
    GENDER_MALE("Male", "gender-male"),
    GENDER_FEMALE("Female", "gender-female"),
    FIRST_NAME("First name", "FirstName"),
    LAST_NAME("Last name", "LastName"),
    EMAIL("Email", "Email"),
    PASSWORD("Password", "Password"),
    CONFIRM_PASSWORD("Confirm password", "ConfirmPassword"),
    ;

    String name;
    String id;

    RegistrationField(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return name;
    }
}
