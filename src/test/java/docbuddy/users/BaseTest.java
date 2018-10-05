package docbuddy.users;

import docbuddy.users.model.User;
import docbuddy.users.persistence.DataStoreManager;
import docbuddy.users.service.responses.LoginResponse;
import org.junit.BeforeClass;

public class BaseTest {
    protected static User TEST_USER;
    protected static final Long TEST_USER_ID = 7357L;
    protected static final String TEST_USER_FIST_NAME = "Test";
    protected static final String TEST_USER_LAST_NAME = "User";
    protected static final String TEST_USER_NAME = "testUserName";
    protected static final String TEST_PASSWORD = "testPassword";
    protected static final String TEST_TOKEN = "klj12h3lk1j23klj12kl";
    protected static final String TEST_SECRET = "testSecret";
    protected static DataStoreManager datastoreManager;
    protected static LoginResponse response;

    @BeforeClass
    public static void setUp() {
        TEST_USER = buildTestUser();
        response = LoginResponse.builder().token(TEST_TOKEN).userId(TEST_USER_ID).build();
    }


    protected static User buildTestUser() {
        return User.builder()
                .id(TEST_USER_ID)
                .firstName(TEST_USER_FIST_NAME)
                .lastName(TEST_USER_LAST_NAME)
                .userName(TEST_USER_NAME)
                .password(TEST_PASSWORD)
                .build();
    }
}
