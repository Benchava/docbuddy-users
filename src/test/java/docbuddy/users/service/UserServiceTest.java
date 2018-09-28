package docbuddy.users.service;

import docbuddy.users.BaseTest;
import docbuddy.users.model.User;
import docbuddy.users.persistence.Result;
import docbuddy.users.persistence.dao.UserDao;
import docbuddy.users.persistence.dao.UserDaoImpl;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest extends BaseTest {
    private static UserService userService;

    @BeforeClass
    public static void prepare() throws SQLException {
        UserDao mockedUserDao = mock(UserDaoImpl.class);
        when(mockedUserDao.createUser(TEST_USER)).thenReturn(TEST_USER_ID);
        when(mockedUserDao.getUser(TEST_USER_ID)).thenReturn(TEST_USER);

        List<User> userList = new ArrayList<>();
        userList.add(TEST_USER);

        when(mockedUserDao.getAllUsers("")).thenReturn(new Result(userList));

        userService = new UserService();
        userService.setUserDao(mockedUserDao);
    }

    @Test
    public void createUserSuccess() {
        Long result = userService.createUser(TEST_USER);

        assertNotNull(result);
        assertEquals(TEST_USER_ID, result);
    }

    @Test
    public void getUserSuccess() throws SQLException {
        User result = userService.getUser(TEST_USER_ID);

        assertNotNull(result);
        assertEquals(TEST_USER, result);
    }

    @Test
    public void getAllUsersSuccess() throws SQLException {
        Result<User> result = userService.getAllUsers();

        assertNotNull(result);
        assertTrue(result.result.size() > 0);
        assertEquals(TEST_USER, result.result.get(0));
    }
}
