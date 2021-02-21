package pet.management.system.restapi.owner;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Introspected
@ToString
@Getter
@AllArgsConstructor
public class Manage {
    @NotBlank
    @Size(min = 3, max = 100)
    private String fullName;

    @NotBlank
    @Size(min = 7, message = "Password must be of minimum 7 characters long.")
    private String password;
}