package docbuddy.users.service.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import docbuddy.users.service.AuthService;
import docbuddy.users.service.request.LoginRequest;
import docbuddy.users.service.responses.LoginResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(UsersControllerTest.class)
public class UsersControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AuthService authService;


    @Test
    public void givenUserReturnJSonArray() throws Exception {
        final LoginRequest request = LoginRequest.builder().userName("john").password("john").build();

        LoginResponse response = LoginResponse.builder().token("klj12h3lk1j23klj12kl").userId(123l).build();

        ObjectMapper mapper = new ObjectMapper();

        when(authService.login(any())).thenReturn(response);

        mvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(mapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token", is(response.getToken())));

    }
}
