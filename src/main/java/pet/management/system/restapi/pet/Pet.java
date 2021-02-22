package pet.management.system.restapi.pet;

import io.micronaut.core.annotation.Introspected;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Introspected
@Getter
@Setter
public class Pet {
    @Max(1000)
    private int _id;
    @NotBlank
    @Max(value = 100, message = "Name cannot be greater than 100 char!")
    private String name;
    @NotBlank
    private String species;
    @NotBlank
    private String breed;
    @NotBlank
    private String color;
    private String owner;

    public Pet(String name, String species, String breed, String color) {
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.color = color;
    }
}
