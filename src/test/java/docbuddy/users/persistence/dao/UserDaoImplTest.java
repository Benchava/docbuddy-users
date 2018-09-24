package docbuddy.users.persistence.dao;

import com.google.cloud.NoCredentials;
import com.google.cloud.ServiceOptions;
import com.google.cloud.datastore.DatastoreOptions;
import docbuddy.users.exceptions.BadRequestException;
import docbuddy.users.model.User;
import docbuddy.users.persistence.DatastoreManager;
import docbuddy.users.persistence.Result;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class UserDaoImplTest {
    private static UserDaoImpl userDao;

    private static User TEST_USER;
    private static final Long TEST_USER_ID = 7357L;
    private static final String TEST_USER_FIST_NAME = "Test";
    private static final String TEST_USER_LAST_NAME = "User";
    private static final String TEST_USER_NAME = "testUserName";
    private static final String TEST_PASSWORD = "testPassword";

    @Before
    public void setUp() {
        DatastoreOptions options = DatastoreOptions.newBuilder()
                .setProjectId(DatastoreOptions.getDefaultProjectId())
                .setHost("localhost:8081")
                .setCredentials(NoCredentials.getInstance())
                .setRetrySettings(ServiceOptions.getNoRetrySettings())
                .build();

        DatastoreManager datastoreManager = new DatastoreManager(options.getService());

        userDao = new UserDaoImpl(datastoreManager);

        TEST_USER = buildTestUser();
    }

    @Test
    public void createUser_success() {
        Long result = userDao.createUser(TEST_USER);

        assertNotNull(result);
    }

    @Test(expected = BadRequestException.class)
    public void createUser_noUserName() {
        TEST_USER.setUserName(null);

        userDao.createUser(TEST_USER);
    }


    @After
    public void cleanUp() {
        Result<User> users = userDao.getAllUsers("");

        if (users != null && !users.result.isEmpty()) {
            for (User user : users.result) {
                userDao.deleteUser(user.getId());
            }
        }
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
