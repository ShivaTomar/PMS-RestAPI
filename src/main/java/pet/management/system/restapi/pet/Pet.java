package pet.management.system.restapi.pet;

import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Introspected
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

    public Pet(int id, String name, String species, String breed, String color) {
        this._id = id;
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getOwner() { return owner; }

    public void setOwner(String owner) { this.owner = owner; }
}
