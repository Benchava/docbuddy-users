package docbuddy.users.exceptions;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class InvalidTokenExceptionTest {
    @Test
    public void createExceptionTest(){
        InvalidTokenException invalidTokenException = new InvalidTokenException();

        assertNotNull(invalidTokenException);
    }
}
