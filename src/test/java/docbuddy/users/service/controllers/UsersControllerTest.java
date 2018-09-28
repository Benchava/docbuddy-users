package docbuddy.users.service.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import docbuddy.users.service.AuthService;
import docbuddy.users.service.request.LoginRequest;
import docbuddy.users.service.responses.LoginResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


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

        MockHttpServletResponse controllerResponse = mvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(mapper.writeValueAsString(request)))
                .andDo(print())
                .andReturn().getResponse();


        Assert.assertEquals(controllerResponse.getStatus(), HttpStatus.OK.value());
        Assert.assertEquals(controllerResponse.getContentAsString(),
                mapper.writeValueAsString(response));
    }
}
