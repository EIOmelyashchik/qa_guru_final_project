package api.testData;

import api.models.NewUser;
import com.github.javafaker.Faker;

public class GenerateData {

    public static NewUser getUser(int minimumLength, int maximumLength, boolean includeUppercase,
                                  boolean includeSpecial, boolean includeDigit) {
        Faker faker = new Faker();
        return NewUser.builder()
                .userName(faker.name().username())
                .password(faker.internet().password(minimumLength, maximumLength, includeUppercase,
                        includeSpecial, includeDigit))
                .build();
    }
}
