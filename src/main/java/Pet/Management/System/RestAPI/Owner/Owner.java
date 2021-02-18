package Pet.Management.System.RestAPI.Owner;

import io.micronaut.core.annotation.Introspected;
import lombok.Getter;
import lombok.ToString;
import javax.validation.constraints.*;

@Introspected
@ToString
@Getter
public class Owner {
    @Max(10000)
    private int _id;

    @NotBlank
    @Size(min = 18, max = 100)
    private String fullName;

    @NotBlank
    private String userName;

    @NotBlank
    @Email(message = "Please enter a valid email!")
    private String email;

    @NotBlank
    @Size(min = 7, message = "Password must be of minimum 7 characters long.")
    private String password;

    @NotNull
    private Address address;

    public Owner(String fullName, String email, String password, String userName, Address address) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.address = address;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}