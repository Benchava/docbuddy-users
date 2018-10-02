package docbuddy.users.exceptions;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class UserNotFoundExceptionTest {
    @Test
    public void createExceptionTest(){
        UserNotFoundException userNotFoundException = new UserNotFoundException();

        assertNotNull(userNotFoundException);
    }
}
