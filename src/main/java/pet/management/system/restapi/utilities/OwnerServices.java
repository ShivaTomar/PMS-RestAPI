package pet.management.system.restapi.utilities;

import pet.management.system.restapi.owner.Manage;
import io.reactivex.Single;
import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.security.Principal;
import java.util.*;

@Singleton
public class OwnerServices {

    private final Map<String, Owner> owners;

    public OwnerServices() {
        owners = new HashMap<>();
    }

    @PostConstruct
    public void createTestUser() {
        owners.put("John_123", new Owner("John Doe", "John_123", "JohnDoe123@gmail.com", "John@123",
                new Address("2748  Green", " Acres Road", "Henderson", "North Carolina", "27536")));
    }

    public Single<Integer> addOwner(Owner owner) {
        int ownerId = owners.size() + 1;
        owner.set_id(ownerId);
        owners.put(owner.getUserName(), owner);
        return Single.just(ownerId);
    }

    public Single<Optional<Owner>> getOwner(Principal user) {
        return Single.just(Optional.ofNullable(owners.get(user.getName())));
    }

    public void updateOwner(Manage updates, Principal user) {
        Owner owner = owners.get(user.getName());

        if (updates.getPassword() != null) {
            owner.setPassword(updates.getPassword());
        }
        if (updates.getFullName() != null) {
            owner.setFullName(updates.getFullName());
        }

        owners.put(user.getName(), owner);
    }

    public String getOwnerPassword(String ownerName) {
        Owner owner = owners.get(ownerName);
        return owner != null ? owner.getPassword() : null;
    }

    public Owner getOwnerAttributes(String ownerName) {
        Owner owner = owners.get(ownerName);
        return new Owner(owner.getFullName(), owner.getUserName(), owner.getEmail(), owner.getAddress());
    }

    public boolean hasUser(String ownerName) {
        return !owners.containsKey(ownerName);
    }
}