package pet.management.system.restapi.register;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.RequestBean;
import pet.management.system.restapi.owner.Owner;
import pet.management.system.restapi.utilities.JsonResponse;
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
    public Single<JsonResponse> register(@Valid @RequestBean Owner owner) {
        if (ownerServices.hasUser(owner.getUserName())) {
            return ownerServices.addOwner(owner).map(success -> new JsonResponse(HttpStatus.CREATED.getCode(), success.toString()));
        }
        return Single.just(Message.REGISTER_FAIL).map(fail -> new JsonResponse(HttpStatus.CREATED.getCode(), fail));
    }
}