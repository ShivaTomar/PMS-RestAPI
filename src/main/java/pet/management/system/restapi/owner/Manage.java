package pet.management.system.restapi.owner;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import javax.annotation.Nullable;
import javax.validation.constraints.Size;

@Introspected
@ToString
@Getter
@AllArgsConstructor
public class Manage {
    @Nullable
    @Size(min = 3, max = 100)
    private String fullName;

    @Nullable
    @Size(min = 7, message = "Password must be of minimum 7 characters long.")
    private String password;
}