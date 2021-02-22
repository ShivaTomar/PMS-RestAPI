package pet.management.system.restapi.utilities;

import io.micronaut.core.annotation.Introspected;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.validation.constraints.*;

@Introspected
@ToString
@Getter
@Setter
public class Owner {
    @Max(10000)
    private int _id;

    @NotBlank
    @Size(min = 3, max = 100)
    private String fullName;

    @NotBlank
    @Size(min = 3, max = 100)
    private String userName;

    @NotBlank
    @Email(message = "Please enter a valid email!")
    private String email;

    @NotBlank
    @Size(min = 7, message = "Password must be of minimum 7 characters long.")
    private String password;

    @NotNull
    private Address address;

    public Owner(String fullName, String userName, String email, String password, Address address) {
        this.fullName = fullName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.address = address;
    }

    public Owner(String fullName, String userName, String email, Address address) {
        this.fullName = fullName;
        this.userName = userName;
        this.email = email;
        this.address = address;
    }
}