package pet.management.system.restapi.register;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.RequestBean;
import io.micronaut.http.exceptions.HttpStatusException;
import pet.management.system.restapi.utilities.Owner;
import pet.management.system.restapi.utilities.JsonObject;
import pet.management.system.restapi.utilities.OwnerServices;
import pet.management.system.restapi.utilities.Message;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.reactivex.Single;
import javax.inject.Inject;
import javax.validation.Valid;

@Controller("/register")
@Secured(SecurityRule.IS_ANONYMOUS)
public class RegisterController {

    @Inject
    private OwnerServices ownerServices;

    @Post
    public Single<JsonObject> register(@Valid @RequestBean Owner owner) {
        if (ownerServices.hasUser(owner.getUserName())) {
            return ownerServices.addOwner(owner).map(success -> new JsonObject(Message.REGISTER_SUCCESS + success));
        }

        return Single.error(new HttpStatusException(HttpStatus.BAD_REQUEST, Message.REGISTER_FAIL));
    }
}