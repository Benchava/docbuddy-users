package docbuddy.users.model;

import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@Builder
public class User {
    private Long id;
    private boolean admin;
    private boolean doctor;
    private String userName;
    private String password;
    private UUID accessToken;
    private String firstName;
    private String lastName;
    private String birth;
    private List<String> specialties;
    private List<String> illnesses;

    public static class Constants {
        public static final String ADMIN = "admin";
        public static final String DOCTOR = "doctor";
        public static final String FIRST_NAME = "first_name";
        public static final String LAST_NAME = "last_name";
        public static final String USERNAME = "username";
        public static final String PASSWORD = "password";
    }

    @Override
    public boolean equals(Object otherUser) {
        if (!(otherUser instanceof User)) return false;

        User other = (User) otherUser;

        return other == this || this.id.equals(other.getId());
    }
}
