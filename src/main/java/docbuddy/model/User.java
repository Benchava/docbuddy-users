package docbuddy.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class User {
    private String id;
    private String userName;
    private String firstName;
    private String lastName;
    private String birth;
}
