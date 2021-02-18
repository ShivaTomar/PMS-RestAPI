package Pet.Management.System.RestAPI.Pet;

import io.reactivex.Maybe;
import io.reactivex.Single;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface PetServices {
    Single<String> addPet(Principal principal, Pet pet);
    Single<List<Pet>> allPets(Principal principal);
    Single<Optional<Pet>> petDetails(Integer id, Principal principal);
    Single<Optional<Pet>> managePet(Integer id, Pet pet, Principal principal);
    Single<Optional<Pet>> deletePet(Integer id, Principal principal);
}
