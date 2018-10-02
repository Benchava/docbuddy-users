package docbuddy.users.service.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import docbuddy.users.BaseControllerTest;
import docbuddy.users.model.User;
import docbuddy.users.persistence.Result;
import docbuddy.users.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@WebMvcTest(UsersController.class)
public class UsersControllerTest extends BaseControllerTest {
    @MockBean
    private UserService userService;

    @Before
    public void prepare() throws SQLException {
        when(userService.createUser(any())).thenReturn(TEST_USER_ID);
        when(userService.getUser(TEST_USER_ID)).thenReturn(TEST_USER);

        List<User> userList = new ArrayList<>();
        userList.add(TEST_USER);

        Result userResult = new Result(userList);

        when(userService.getAllUsers()).thenReturn(userResult);
//        when(userService.updateUser(TEST_USER)).thenReturn(null);
    }

    @Test
    public void createUserSuccess() throws Exception {
        MockHttpServletResponse controllerResponse = mvc.perform(put("/users/add")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(mapper.writeValueAsString(TEST_USER)))
                .andDo(print())
                .andReturn().getResponse();

        assertNotNull(controllerResponse);
        assertEquals(HttpStatus.OK.value(), controllerResponse.getStatus());
        assertEquals(TEST_USER_ID, new Long(controllerResponse.getContentAsString()));
    }

    @Test
    public void createUserNoIdSuccess() throws Exception {
        MockHttpServletResponse controllerResponse = mvc.perform(put("/users/add")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andDo(print())
                .andReturn().getResponse();

        assertNotNull(controllerResponse);
        assertEquals(HttpStatus.BAD_REQUEST.value(), controllerResponse.getStatus());
    }

    @Test
    public void getUserSuccess() throws Exception {
        MockHttpServletResponse controllerResponse = mvc.perform(get("/users/get")
                .param("id", TEST_USER_ID.toString()))
                .andDo(print())
                .andReturn().getResponse();

        assertNotNull(controllerResponse);
        assertEquals(HttpStatus.OK.value(), controllerResponse.getStatus());
        assertEquals(TEST_USER, mapper.readValue(controllerResponse.getContentAsString(), User.class));
    }

    @Test
    public void getUserNoIdSuccess() throws Exception {
        MockHttpServletResponse controllerResponse = mvc.perform(get("/users/get"))
                .andDo(print())
                .andReturn().getResponse();

        assertNotNull(controllerResponse);
        assertEquals(HttpStatus.BAD_REQUEST.value(), controllerResponse.getStatus());
    }

    @Test
    public void getAllUsersSuccess() throws Exception {
        MockHttpServletResponse controllerResponse = mvc.perform(get("/users/get/all"))
                .andDo(print())
                .andReturn().getResponse();

        assertNotNull(controllerResponse);
        assertEquals(HttpStatus.OK.value(), controllerResponse.getStatus());

        String stringToCast = controllerResponse.getContentAsString();

        Result resultResponse = mapper.readValue(stringToCast, new TypeReference<Result<User>>() {
        });

        assertEquals(TEST_USER, resultResponse.getResult().get(0));
    }

    @Test
    public void updateUsersSuccess() throws Exception {
        MockHttpServletResponse controllerResponse = mvc.perform(patch("/users/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(TEST_USER)))
                .andDo(print())
                .andReturn().getResponse();

        assertNotNull(controllerResponse);
        assertEquals(HttpStatus.OK.value(), controllerResponse.getStatus());
    }

    @Test
    public void deleteUsersSuccess() throws Exception {
        MockHttpServletResponse controllerResponse = mvc.perform(delete("/users/delete")
                .param("id", TEST_USER_ID.toString()))
                .andDo(print())
                .andReturn().getResponse();

        assertNotNull(controllerResponse);
        assertEquals(HttpStatus.OK.value(), controllerResponse.getStatus());
    }
}
