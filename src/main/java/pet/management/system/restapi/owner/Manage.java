package pet.management.system.restapi.owner;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Introspected
@Getter
@Setter
@AllArgsConstructor
public class Manage {
    @NotBlank
    private String fullName;

    @NotBlank
    @Size(min = 7, message = "Password must be of minimum 7 characters long.")
    private String password;
}
