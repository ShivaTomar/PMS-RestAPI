package pet.management.system.restapi.utilities;

import java.util.Base64;

public class Base64BasicEncryption {

    public static String passwordEncrypt(String plainPassword) {
        Base64.Encoder encoder = Base64.getMimeEncoder();
        return encoder.encodeToString(plainPassword.getBytes());
    }

    public static String passwordDecode(String encryptedPassword) {
        Base64.Decoder decoder = Base64.getMimeDecoder();
        return new String(decoder.decode(encryptedPassword));
    }
}