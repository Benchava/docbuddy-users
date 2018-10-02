package docbuddy.users.service;

import docbuddy.users.BaseTest;
import docbuddy.users.persistence.dao.UserDao;
import docbuddy.users.persistence.dao.UserDaoImpl;
import docbuddy.users.service.request.LoginRequest;
import docbuddy.users.service.responses.LoginResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthServiceTest extends BaseTest {
    private AuthServiceImpl authService;
    private UserDao mockedDao;

    @Before
    public void prepare() {
        authService = new AuthServiceImpl();
        mockedDao = mock(UserDaoImpl.class);
        authService.setUserDao(mockedDao);
        authService.setSecret(TEST_SECRET);

        when(mockedDao.login(any())).thenReturn(TEST_USER);
    }

    @Test
    public void loginSuccess() {
        LoginRequest loginRequest = LoginRequest.builder()
                .userName(TEST_USER.getUserName())
                .password(TEST_USER.getPassword())
                .build();

        LoginResponse loginResponse = authService.login(loginRequest);

        assertNotNull(loginResponse);
        assertEquals(TEST_USER_ID, loginResponse.getUserId());
    }

    @Test
    public void loginNoUserSuccess() {
        when(mockedDao.login(any())).thenReturn(null);

        LoginRequest loginRequest = LoginRequest.builder()
                .userName(TEST_USER.getUserName())
                .password(TEST_USER.getPassword())
                .build();

        LoginResponse loginResponse = authService.login(loginRequest);

        assertNotNull(loginResponse);
        Assert.assertNull(loginResponse.getToken());
        Assert.assertNull(loginResponse.getUserId());
    }

}
