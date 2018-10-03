package docbuddy.users.util;

import org.mindrot.jbcrypt.BCrypt;

public class BCryptUtil {


    public static BCryptPassword encodePassword(String plainText) {

        BCryptPassword hashedPassword = new BCryptPassword();

        hashedPassword.setSalt(BCrypt.gensalt());

        hashedPassword.setPassword(BCrypt.hashpw(plainText, hashedPassword.getSalt()));

        return hashedPassword;

    }

    public static boolean checkPassword(String plainText, String hashedPassword) {
        return BCrypt.checkpw(plainText, hashedPassword);
    }
}
