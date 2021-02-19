package pet.management.system.restapi.owner;

import io.micronaut.core.annotation.Introspected;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Introspected
@ToString
@Getter
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

    public Address(String line1, String line2, String district, String state, String pincode) {
        this.line1 = line1;
        this.line2 = line2;
        this.district = district;
        this.state = state;
        this.pincode = pincode;
    }
}
