package docbuddy.users.persistence.dao;

import com.google.cloud.NoCredentials;
import com.google.cloud.ServiceOptions;
import com.google.cloud.datastore.DatastoreOptions;
import docbuddy.users.BaseTest;
import docbuddy.users.exceptions.BadRequestException;
import docbuddy.users.model.User;
import docbuddy.users.persistence.DataStoreManager;
import docbuddy.users.persistence.Result;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class UserDaoImplTest extends BaseTest {
    private static UserDaoImpl userDao;

    @BeforeClass
    public static void prepare() {
        DatastoreOptions options = DatastoreOptions.newBuilder()
                .setProjectId(DatastoreOptions.getDefaultProjectId())
                .setHost("localhost:8081")
                .setCredentials(NoCredentials.getInstance())
                .setRetrySettings(ServiceOptions.getNoRetrySettings())
                .build();

        datastoreManager = new DataStoreManager(options.getService());

        userDao = new UserDaoImpl(datastoreManager);
    }

    @Test
    public void createUserSuccess() {
        Long result = userDao.createUser(TEST_USER);

        assertNotNull(result);
    }

    @Test(expected = BadRequestException.class)
    public void createUserNoUserName() {
        TEST_USER.setUserName(null);

        userDao.createUser(TEST_USER);
    }

    @Test(expected = BadRequestException.class)
    public void createUserNoUserPassword() {
        TEST_USER.setPassword(null);

        userDao.createUser(TEST_USER);
    }

    @Test(expected = BadRequestException.class)
    public void getUserNoId() {
        userDao.getUser(null);
    }

    @Test(expected = BadRequestException.class)
    public void updateUserNoUserName() {
        TEST_USER.setUserName(null);

        userDao.updateUser(TEST_USER);
    }

    @Test(expected = BadRequestException.class)
    public void updateUserNoUserPassword() {
        TEST_USER.setPassword(null);

        userDao.updateUser(TEST_USER);
    }

    @Test(expected = BadRequestException.class)
    public void deleteUserNoId() {
        userDao.deleteUser(null);
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

}
