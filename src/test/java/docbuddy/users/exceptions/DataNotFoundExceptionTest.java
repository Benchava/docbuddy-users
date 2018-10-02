package docbuddy.users.exceptions;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class DataNotFoundExceptionTest {
    @Test
    public void createExceptionTest(){
        DataNotFoundException dataNotFoundException = new DataNotFoundException();

        assertNotNull(dataNotFoundException);
    }
}
