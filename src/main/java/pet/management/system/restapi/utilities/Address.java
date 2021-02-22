package pet.management.system.restapi.utilities;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import javax.validation.constraints.NotBlank;

@Introspected
@ToString
@Getter
@AllArgsConstructor
public class Address {
    @NotBlank
    private final String line1;

    @NotBlank
    private final String line2;

    @NotBlank
    private final String district;

    @NotBlank
    private final String state;

    @NotBlank
    private final String pincode;
}
