package docbuddy.users.util;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class BCryptUtil {


    public BCryptPassword encodePassword(String plainText) {

        BCryptPassword hashedPassword = new BCryptPassword();

        hashedPassword.setSalt(BCrypt.gensalt());

        hashedPassword.setPassword(BCrypt.hashpw(plainText, hashedPassword.getSalt()));

        return hashedPassword;

    }

    public boolean checkPassword(String plainText, String hashedPassword) {
        return BCrypt.checkpw(plainText, hashedPassword);
    }
}
