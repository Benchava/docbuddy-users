package docbuddy.users;

import docbuddy.users.model.User;
import docbuddy.users.persistence.dao.UserDaoImpl;
import docbuddy.users.service.AuthService;
import docbuddy.users.service.request.LoginRequest;
import docbuddy.users.service.responses.LoginResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@WebMvcTest
public class BaseControllerTest extends BaseTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private static UserDaoImpl userDao;

    @Autowired
    private static AuthService authService;

    protected static User authenticatedUser;
    protected static String authenticatedToken;

//    @BeforeClass
//    public static void setUpUser() {
//        createUserForAuthentication();
//
//        LoginResponse loginResponse = authenticateUser();
//
//        if (loginResponse != null && !Strings.isNullOrEmpty(loginResponse.getToken())) {
//            authenticatedToken = loginResponse.getToken();
//        }
//    }

    @Test
    public void test(){
        assertTrue(true);
    }

    private static User buildUserForAuthentication() {
        return User.builder()
                .admin(true)
                .doctor(true)
                .firstName("Authenticated")
                .lastName("User")
                .userName("admin")
                .password("pass")
                .build();
    }

    private static void createUserForAuthentication() {
        authenticatedUser = buildUserForAuthentication();

        Long id = userDao.createUser(authenticatedUser);

        if (id != null) {
            authenticatedUser.setId(id);
        }
    }

    private static LoginResponse authenticateUser() {
        LoginRequest loginRequest = LoginRequest.builder()
                .userName(authenticatedUser.getUserName())
                .password(authenticatedUser.getPassword())
                .build();

        return authService.login(loginRequest);
    }
}
