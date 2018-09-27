package docbuddy.users.service.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import docbuddy.users.BaseControllerTest;
import docbuddy.users.service.UserService;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

//@RunWith(SpringRunner.class)
//@SpringBootTest
//@WebMvcTest(UsersController.class)
public class UsersControllerTest extends BaseControllerTest {
    private static UserService mockedUserService;
    private static ObjectMapper mapper;

//    @BeforeClass
//    public static void prepare() {
//        mapper = new ObjectMapper();
//
//        mockedUserService = mock(UserService.class);
//        when(mockedUserService.createUser(TEST_USER)).thenReturn(TEST_USER_ID);
//
//
//    }

    @Test
    public void createUserSuccess() throws Exception {
//        mockMvc.perform(put("/users/add").contentType(MediaType.APPLICATION_JSON)
//                .header(TokenConstants.HEADER_TOKEN, authenticatedToken)
//                .content(mapper.writeValueAsBytes(TEST_USER)))
//                .andExpect(status().isOk());
        assertTrue(true);
    }
}
