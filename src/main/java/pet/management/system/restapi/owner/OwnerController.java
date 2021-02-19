package pet.management.system.restapi.owner;

import io.micronaut.core.util.CollectionUtils;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Put;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.validation.Validated;
import io.reactivex.Single;
import javax.inject.Inject;
import java.security.Principal;
import java.util.*;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/owner")
@Validated
public class OwnerController {

    @Inject
    private OwnerServices ownerServices;

    @Get
    public Single<Object> getOwner(Authentication authentication) {
        return Single.just(CollectionUtils.mapOf(
                "isLoggedIn", true,
                "username", authentication.getName(),
                "roles", authentication.getAttributes().get("roles"),
                "attributes", ownerServices.getOwnerAttributes(authentication.getName())
        ));
    }

    @Put
    public Single<HttpStatus> manageOwner(@Body Owner updates, Principal user) {
        ownerServices.manageOwner(updates, user);
        return Single.just(HttpStatus.OK);
    }

    //this endpoint is for testing purpose.
    @Get("/all")
    public Single<List<Owner>> allOwners() {
        return ownerServices.allOwners();
    }
}