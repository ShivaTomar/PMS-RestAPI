package pet.management.system.restapi.owner;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.validation.constraints.*;

@Introspected
@ToString
@Getter
@Setter
@AllArgsConstructor
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
}