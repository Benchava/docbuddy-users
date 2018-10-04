package docbuddy.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import docbuddy.users.service.request.LoginRequest;

public class BaseControllerTest extends BaseTest {
    @Autowired
    protected MockMvc mvc;

 
    @Autowired
    protected ObjectMapper mapper;
    protected LoginRequest request;

   
}
