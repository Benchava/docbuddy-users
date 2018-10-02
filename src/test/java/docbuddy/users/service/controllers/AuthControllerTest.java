package docbuddy.users.service.controllers;

import docbuddy.users.BaseControllerTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@RunWith(SpringRunner.class)
@WebMvcTest(AuthControllerTest.class)
public class AuthControllerTest extends BaseControllerTest {

    @Test
    public void givenUserReturnJSonArray() throws Exception {
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
