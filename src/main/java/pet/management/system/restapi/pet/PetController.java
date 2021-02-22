package pet.management.system.restapi.pet;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.reactivex.Maybe;
import io.reactivex.Single;
import pet.management.system.restapi.utilities.JsonObject;
import pet.management.system.restapi.utilities.Message;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/pets")
public class PetController {

    @Inject
    private PetServices petServices;

    @Post
    public Single<JsonObject> addPet(Principal user, @Valid @RequestBean Pet pet) {
        return petServices.addPet(user, pet)
                .map(success -> new JsonObject(Message.PET_ADDED_SUCCESS + success));
    }

    @Get
    public Single<List<Pet>> allPets(Principal user) {
        return petServices.allPets(user);
    }

    @Get("/{id}")
    public Single<Optional<Pet>> petDetails(@NotBlank Integer id, Principal user) {
        return petServices.petDetails(id, user);
    }

    @Put("/{id}")
    public Maybe<HttpStatus> updatePet(@NotBlank Integer id, @Valid @RequestBean Pet pet, Principal user) {
        return petServices.updatePet(id, pet, user)
                .filter((result) -> !result.equals(Optional.empty()))
                .map((success) -> {
                    return HttpStatus.OK;
                });
    }

    @Delete("/{id}")
    public Maybe<HttpStatus> deletePet(@NotBlank Integer id, Principal user) {
        return petServices.deletePet(id, user)
                .filter(result -> !result.equals(Optional.empty()))
                .map(success -> {
                    return HttpStatus.OK;
                });
    }
}