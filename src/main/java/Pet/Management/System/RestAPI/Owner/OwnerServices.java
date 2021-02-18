package Pet.Management.System.RestAPI.Owner;

import io.reactivex.Single;
import java.security.Principal;
import java.util.Optional;

public interface OwnerServices {
    Single<String> addOwner(Owner owner);
    Single<Optional<Owner>> getOwner(Principal principal);
    void manageOwner(Owner manage, Principal user);
}
