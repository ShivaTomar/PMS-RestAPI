package pet.management.system.restapi.utilities;

import javax.inject.Singleton;

@Singleton
public class Messages {
    public String authenticationFail = "Requires Authentication!";
    public String registerFail = "User with this username already exists please try with a different username.";
    public String registerSuccess = "New user has been added with id:";
    public String petAddedSuccess = "A New Pet has been successfully added by: ";
}
