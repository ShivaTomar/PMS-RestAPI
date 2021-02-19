package pet.management.system.restapi.auth;

import pet.management.system.restapi.owner.OwnerServices;
import edu.umd.cs.findbugs.annotations.Nullable;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.*;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collections;

@Singleton
public class Auth implements AuthenticationProvider {

    @Inject
    private OwnerServices ownerServices;

    @Override
    public Publisher<AuthenticationResponse> authenticate(@Nullable HttpRequest<?> httpRequest, AuthenticationRequest<?, ?> authenticationRequest) {
        String username = authenticationRequest.getIdentity().toString();
        String password = authenticationRequest.getSecret().toString();

        if(ownerServices.hasUser(username)) {
            return Flowable.just(new AuthenticationFailed(AuthenticationFailureReason.USER_NOT_FOUND));
        }

        if(password.equals(ownerServices.getOwnerPassword(username))) {
            UserDetails details = new UserDetails(
                    username,
                    Collections.singletonList("Owner"),
                    Collections.singletonMap(username, ownerServices.getOwnerAttributes(username)));
            return Flowable.just(details);
        }
        return Flowable.just(new AuthenticationFailed(AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH));
    }
}