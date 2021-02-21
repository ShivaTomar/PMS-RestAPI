package pet.management.system.restapi.owner;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.validation.Validated;
import io.reactivex.Single;
import pet.management.system.restapi.utilities.OwnerServices;
import javax.inject.Inject;
import javax.validation.Valid;
import java.security.Principal;
import java.util.*;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/owner")
@Validated
public class OwnerController {

    @Inject
    private OwnerServices ownerServices;

    @Get
    public Single<Owner> getOwner(Authentication authentication) {
        return Single.just(ownerServices.getOwnerAttributes(authentication.getName()));
    }

    @Patch
    public Single<HttpStatus> updateOwner(@Valid @RequestBean Manage updates, Principal user) {
        ownerServices.updateOwner(updates, user);
        return Single.just(HttpStatus.OK);
    }

    //this endpoint is for testing purpose.
    @Get("/all")
    public Single<List<Owner>> allOwners() {
        return ownerServices.allOwners();
    }
}