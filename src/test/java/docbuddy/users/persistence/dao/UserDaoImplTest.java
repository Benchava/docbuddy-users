package docbuddy.users.persistence.dao;

import com.google.cloud.NoCredentials;
import com.google.cloud.ServiceOptions;
import com.google.cloud.datastore.DatastoreOptions;
import docbuddy.users.BaseTest;
import docbuddy.users.exceptions.BadRequestException;
import docbuddy.users.exceptions.UserNotFoundException;
import docbuddy.users.model.User;
import docbuddy.users.persistence.DataStoreManager;
import docbuddy.users.persistence.Result;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
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

    @Test
    public void getUser() {
        User localTestUser = buildTestUser();

        Long localTestUserId = userDao.createUser(localTestUser);

        User resultUser = userDao.getUser(localTestUserId);

        assertNotNull(resultUser);
        assertEquals(localTestUserId, resultUser.getId());
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

    @Test
    public void updateUserSuccess() {
        final String UPDATED_LAST_NAME = "updatedLasName";
        User localUpdateTestUser = buildTestUser();

        Long localUpdateTestUserId = userDao.createUser(localUpdateTestUser);

        localUpdateTestUser.setId(localUpdateTestUserId);
        localUpdateTestUser.setLastName(UPDATED_LAST_NAME);

        userDao.updateUser(localUpdateTestUser);

        User result = userDao.getUser(localUpdateTestUserId);

        assertNotNull(result);
        assertEquals(localUpdateTestUserId, result.getId());
        assertEquals(UPDATED_LAST_NAME, result.getLastName());
    }

    @Test(expected = BadRequestException.class)
    public void deleteUserNoId() {
        userDao.deleteUser(null);
    }

    @Test
    public void getAllUsersEmptyCursorSuccess() {
        User localTestUser = buildTestUser();

        userDao.createUser(localTestUser);

        Result<User> result = userDao.getAllUsers("");

        assertNotNull(result);
        assertTrue(result.getResult().size()>0);
    }

    @Test
    public void getAllUsersNullCursorSuccess() {
        User localTestUser = buildTestUser();

        userDao.createUser(localTestUser);

        Result<User> result = userDao.getAllUsers(null);

        assertNotNull(result);
        assertTrue(result.getResult().size()>0);
    }

    @Test
    public void getAllUsersValidCursorSuccess() {
        User localTestUser = buildTestUser();

        userDao.createUser(localTestUser);

        Result<User> result = userDao.getAllUsers("testCursor");

        assertNotNull(result);
        assertTrue(result.getResult().size()>0);
    }

    @Test(expected = UserNotFoundException.class)
    public void loginNoUser() {
        User localTestUser = buildTestUser();
        localTestUser.setId(888888L);

        userDao.login(localTestUser);
    }

    @Test(expected = UserNotFoundException.class)
    public void loginUserChangedPassword() {
        User localTestUser = buildTestUser();

        Long localTestUserId = userDao.createUser(localTestUser);
        localTestUser.setId(localTestUserId);
        localTestUser.setPassword("changedPassword");

        userDao.login(localTestUser);
    }

    @Test
    public void loginUserSuccess() {
        User localTestUser = buildTestUser();

        Long localTestUserId = userDao.createUser(localTestUser);
        localTestUser.setId(localTestUserId);

        User result = userDao.login(localTestUser);

        assertNotNull(result);
        assertEquals(localTestUser, result);
    }



    @After
    public void cleanUp() {
        Result<User> users = userDao.getAllUsers("");

        if (users != null && !users.getResult().isEmpty()) {
            for (User user : users.getResult()) {
                userDao.deleteUser(user.getId());
            }
        }
    }

}
