package Pet.Management.System.RestAPI.Owner;

import Pet.Management.System.RestAPI.Utilities.Messages;
import io.reactivex.Single;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.security.Principal;
import java.util.*;

@Singleton
public class OwnerServicesImpl implements OwnerServices {

    @Inject
    private Messages message;

    private final Map<String, Owner> owners;

    public OwnerServicesImpl() {
        owners = new HashMap<>();
    }

    @Override
    public Single<String> addOwner(Owner owner) {
        int ownerId = owners.size() + 1;
        owner.set_id(ownerId);
        owners.put(owner.getUserName(), owner);

        return Single.just(message.registerSuccess +ownerId);
    }

    @Override
    public Single<Optional<Owner>> getOwner(Principal principal) {
        return Single.defer(() -> Single.just(Optional.ofNullable(owners.get(principal.getName()))));
    }

    @Override
    public void manageOwner(Owner updates, Principal user) {
        Owner owner = owners.get(user.getName());
        owner.setPassword(updates.getPassword());
        owner.setFullName(updates.getFullName());
        owners.put(user.getName(), owner);
    }

    public Single<List<Owner>> allOwners() {
        return Single.just(new ArrayList<>(owners.values()));
    }

    public String getOwnerPassword(String ownerName) {
        Owner owner = owners.get(ownerName);
        return owner != null ? owner.getPassword() : null;
    }

    public Owner getOwnerAttributes(String ownerName) {
        return owners.get(ownerName);
    }

    public boolean IsUniqueOwner(String ownerName) {
        return !owners.containsKey(ownerName);
    }
}