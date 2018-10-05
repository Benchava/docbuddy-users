package docbuddy.users.util;

import docbuddy.users.exceptions.UserNotFoundException;
import org.mindrot.jbcrypt.BCrypt;

public class BCryptUtil {


    public static BCryptPassword encodePassword(String plainText) {

        BCryptPassword hashedPassword = new BCryptPassword();

        hashedPassword.setSalt(BCrypt.gensalt());

        hashedPassword.setPassword(BCrypt.hashpw(plainText, BCrypt.gensalt()));

        return hashedPassword;

    }

    public static boolean checkPassword(String plainText, String hashedPassword) {
        try {
            return BCrypt.checkpw(plainText, hashedPassword);
        } catch (IllegalArgumentException iae) {
            throw new UserNotFoundException();
        }
    }
}
