package api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewUser {
    private String userName;
    private String password;

    @Override
    public String toString() {
        return "{ \"username\": \"" + userName + "\", \"password\": \"" + password + "\" }";
    }
}
