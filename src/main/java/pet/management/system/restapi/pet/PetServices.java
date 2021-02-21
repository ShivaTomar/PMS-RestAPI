package pet.management.system.restapi.pet;

import pet.management.system.restapi.utilities.Message;
import io.reactivex.Single;

import javax.inject.Singleton;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Singleton
public class PetServices {
    private final Map<Integer, Pet> pets;

    public PetServices() {
        pets = new HashMap<>();
    }

    public Single<Integer> addPet(Principal user, Pet pet) {
        int petId = pets.size() + 1;
        pet.set_id(petId);
        pet.setOwner(user.getName());
        pets.put(petId, pet);

        return Single.just(petId);
    }

    public Single<List<Pet>> allPets(Principal user) {
        List<Pet> result = pets.values().stream().filter(pet -> pet.getOwner().equals(user.getName())).collect(Collectors.toList());
        return Single.just(result);
    }

    public Single<Optional<Pet>> petDetails(Integer id, Principal user) {
        return Single.just(Optional.ofNullable(findAndGet(id, user)));
    }

    public Single<Optional<Pet>> updatePet(Integer id, Pet pet, Principal user) {
        return Single.just(Optional.ofNullable(findAndUpdate(id, pet, user)));
    }

    public Single<Optional<Pet>> deletePet(Integer id, Principal user) {
        return Single.just((IsPetPresent(id) && IsOwner(pets.get(id).getOwner(), user))).map(result -> {
            return !result ? Optional.empty() : Optional.of(findAndDelete(id));
        });
    }

    public Single<List<Pet>> allUsersPet() {
        return Single.just(new ArrayList<>(pets.values()));
    }

    public Pet findAndGet(Integer id, Principal user) {
        Pet detail = null;
        if (IsPetPresent(id)) {
            Pet pet = pets.get(id);
            if (IsOwner(pet.getOwner(), user)) {
                detail = pet;
            }
        }
        return detail;
    }

    public Pet findAndUpdate(Integer id, Pet pet, Principal user) {
        Pet details = null;
        if (IsPetPresent(id)) {
            Pet curPet = pets.get(id);
            if (IsOwner(curPet.getOwner(), user)) {
                pet.set_id(curPet.get_id());
                pet.setOwner(curPet.getOwner());
                pets.put(id, pet);
                details = pet;
            }
        }
        return details;
    }

    public Pet findAndDelete(Integer id) {
        return pets.remove(id);
    }

    public boolean IsOwner(String owner, Principal user) {
        return owner.equals(user.getName());
    }

    public boolean IsPetPresent(Integer id) {
        return pets.containsKey(id);
    }
}