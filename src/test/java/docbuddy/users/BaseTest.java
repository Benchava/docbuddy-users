package docbuddy.users;

import docbuddy.users.model.User;
import docbuddy.users.persistence.DataStoreManager;
import org.junit.BeforeClass;

public class BaseTest {
    protected static User TEST_USER;
    protected static final Long TEST_USER_ID = 7357L;
    protected static final String TEST_USER_FIST_NAME = "Test";
    protected static final String TEST_USER_LAST_NAME = "User";
    protected static final String TEST_USER_NAME = "testUserName";
    protected static final String TEST_PASSWORD = "testPassword";
    protected static DataStoreManager datastoreManager;

    @BeforeClass
    public static void setUp() {
        TEST_USER = buildTestUser();
    }


    private static User buildTestUser() {
        return User.builder()
                .id(TEST_USER_ID)
                .firstName(TEST_USER_FIST_NAME)
                .lastName(TEST_USER_LAST_NAME)
                .userName(TEST_USER_NAME)
                .password(TEST_PASSWORD)
                .build();
    }
}
