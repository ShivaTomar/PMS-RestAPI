package Pet.Management.System.RestAPI.Auth;

import Pet.Management.System.RestAPI.Owner.Owner;
import Pet.Management.System.RestAPI.Owner.OwnerServicesImpl;
import Pet.Management.System.RestAPI.Utilities.Messages;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.reactivex.Single;
import javax.inject.Inject;

@Controller("/register")
@Secured(SecurityRule.IS_ANONYMOUS)
public class RegisterController {

    @Inject
    private OwnerServicesImpl ownerServices;

    @Inject
    private Messages message;

    @Post
    public Single<HttpResponse<String>> register(@Body Owner owner) {
        if (ownerServices.IsUniqueOwner(owner.getUserName())) {
            return ownerServices.addOwner(owner).map(success -> HttpResponse.created(success));
        }
        return Single.just(message.registerFail).map(fail -> HttpResponse.badRequest(fail));
    }
}