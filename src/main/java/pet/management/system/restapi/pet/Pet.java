package pet.management.system.restapi.pet;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Introspected
@Getter
@Setter
@AllArgsConstructor
public class Pet {
    @Max(1000)
    private int _id;

    @NotBlank
    private String name;

    @NotBlank
    private String species;

    @NotBlank
    private String breed;

    @NotBlank
    private String color;

    private String owner;
}
