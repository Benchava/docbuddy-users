package docbuddy.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import docbuddy.users.service.AuthService;
import docbuddy.users.service.request.LoginRequest;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class BaseControllerTest extends BaseTest {
    @Autowired
    protected MockMvc mvc;

    @MockBean
    protected AuthService authService;

    @Autowired
    protected ObjectMapper mapper;
    protected LoginRequest request;

    @Before
    public void setUpUser() {
        request = LoginRequest.builder().userName(TEST_USER_NAME).password(TEST_PASSWORD).build();

        when(authService.login(any())).thenReturn(response);
    }
}
