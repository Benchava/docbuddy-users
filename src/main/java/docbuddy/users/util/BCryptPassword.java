package docbuddy.users.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BCryptPassword {

    private String salt;

    private String password;
}
