package ui.testData;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {
    private String firstName;
    private String lastName;
    private String email;
    private Country country;
    private String city;
    private String address;
    private String zipCode;
    private String phoneNumber;
}
