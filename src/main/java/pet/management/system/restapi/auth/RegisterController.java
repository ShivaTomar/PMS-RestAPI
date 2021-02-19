package pet.management.system.restapi.auth;

import pet.management.system.restapi.owner.Owner;
import pet.management.system.restapi.owner.OwnerServices;
import pet.management.system.restapi.utilities.Messages;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
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

    @Inject
    private Messages message;

    @Post
    public Single<HttpResponse<String>> register(@Valid @Body Owner owner) {
        if (ownerServices.hasUser(owner.getUserName())) {
            return ownerServices.addOwner(owner).map(success -> HttpResponse.created(success));
        }
        return Single.just(message.registerFail).map(fail -> HttpResponse.badRequest(fail));
    }
}