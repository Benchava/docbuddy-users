package docbuddy.users.service.controllers;

import docbuddy.users.BaseControllerTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(HealthCheckController.class)
public class HealthCheckControllerTest extends BaseControllerTest {

    private final String DEFAULT_RESPONSE = "Service is up %s!!";

    @Test
    public void healthCheckNoNameSuccess() throws Exception {
        final String DEFAULT_NAME = "Dr.";
        mvc.perform(get("/healthcheck"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description", is(String.format(DEFAULT_RESPONSE, DEFAULT_NAME))));
    }

    @Test
    public void healthCheckWithNameSuccess() throws Exception {
        final String TEST_NAME = "John";
        mvc.perform(get(String.format("/healthcheck?name=%s", TEST_NAME)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description", is(String.format(DEFAULT_RESPONSE, TEST_NAME))));
    }
}
