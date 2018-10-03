package docbuddy.users.exceptions;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ServerExceptionTest {

    @Test
    public void createExceptionWithMessageTest(){
        final String TEST_MESSAGE = "Test message for server exception";
        ServerException serverException = new ServerException(TEST_MESSAGE);

        assertNotNull(serverException);
        assertEquals(TEST_MESSAGE, serverException.getMessage());
    }

    @Test
    public void createExceptionWithOtherExceptionTest(){
        final String TEST_MESSAGE = "Test message for server exception";
        Exception exception = new Exception(TEST_MESSAGE);
        ServerException serverException = new ServerException(exception);

        assertNotNull(serverException);
        assertEquals("java.lang.Exception: " + TEST_MESSAGE, serverException.getMessage());
    }
}
