package docbuddy.users.exceptions;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class InvalidAccessExceptionTest {
    @Test
    public void createExceptionTest(){
        InvalidAccessException invalidAccessException = new InvalidAccessException();

        assertNotNull(invalidAccessException);
    }
}
