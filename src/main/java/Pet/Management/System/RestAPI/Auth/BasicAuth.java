package Pet.Management.System.RestAPI.Auth;

import Pet.Management.System.RestAPI.Owner.OwnerServicesImpl;
import Pet.Management.System.RestAPI.Utilities.Messages;
import edu.umd.cs.findbugs.annotations.Nullable;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.*;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collections;

@Singleton
public class BasicAuth implements AuthenticationProvider {

    @Inject
    private OwnerServicesImpl ownerServices;

    @Inject
    private Messages message;

    @Override
    public Publisher<AuthenticationResponse> authenticate(@Nullable HttpRequest<?> httpRequest, AuthenticationRequest<?, ?> authenticationRequest) {
        String username = authenticationRequest.getIdentity().toString();
        String password = authenticationRequest.getSecret().toString();

        if(password.equals(ownerServices.getOwnerPassword(username))) {
            UserDetails details = new UserDetails(
                    username,
                    Collections.singletonList("Owner"),
                    Collections.singletonMap(username, ownerServices.getOwnerAttributes(username)));
            return Flowable.just(details);
        }
        return Flowable.just(new AuthenticationFailed(message.authenticationFail));
    }
}