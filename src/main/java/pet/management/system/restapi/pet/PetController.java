package pet.management.system.restapi.pet;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.reactivex.Maybe;
import io.reactivex.Single;
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
    public Single<HttpResponse<String>> addPet(Principal user, @Valid @Body Pet pet) {
        return petServices.addPet(user, pet).map(HttpResponse::created);
    }

    @Get
    public Single<List<Pet>> allPets(Principal user) {
        return petServices.allPets(user);
    }

    @Get("/{id}")
    public Single<Optional<Pet>> petDetails(@PathVariable @NotBlank Integer id, Principal user) {
        return petServices.petDetails(id, user);
    }

    @Put("/{id}")
    public Maybe<HttpStatus> managePet(@PathVariable @NotBlank Integer id, @Valid @Body Pet pet, Principal user) {
        return petServices.managePet(id, pet, user)
                .filter((result) -> !result.equals(Optional.empty()))
                .map((success) -> {
                    return HttpStatus.OK;
        });
    }

    @Delete("/{id}")
    public Maybe<HttpStatus> deletePet(@PathVariable @NotBlank Integer id, Principal user) {
        return petServices.deletePet(id, user)
                .filter(result -> !result.equals(Optional.empty()))
                .map(success -> {
                    return HttpStatus.OK;
        });
    }

    //this endpoint is for testing purpose.
    @Get("/all")
    public Single<List<Pet>> allUserPet() {
        return petServices.allUsersPet();
    }
}