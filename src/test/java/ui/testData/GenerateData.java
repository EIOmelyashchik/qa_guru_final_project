package ui.testData;

import com.github.javafaker.Faker;

public class GenerateData {

    public static Customer getCustomer() {
        Faker faker = new Faker();
        return Customer.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .country(getRandomCountry(faker))
                .city(faker.address().city())
                .address(faker.address().streetAddress())
                .zipCode(faker.address().zipCode())
                .phoneNumber(faker.phoneNumber().cellPhone())
                .build();

    }

    private static Country getRandomCountry(Faker faker) {
        Country[] countries = Country.values();
        return countries[faker.random().nextInt(0, countries.length - 1)];
    }

    public static User getUser() {
        Faker faker = new Faker();
        return User.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .password(faker.internet().password())
                .build();
    }
}
