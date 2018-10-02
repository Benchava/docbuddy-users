package docbuddy.users.util;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class BCryptUtilTest {
    private final String TEST_PASSWORD = "TestPassword";
    private String encodedPassword;
    private BCryptUtil bCryptUtil;

    @Before
    public void setUp() {
        bCryptUtil = new BCryptUtil();
        encodedPassword = bCryptUtil.encodePassword(TEST_PASSWORD).getPassword();
    }

    @Test
    public void checkPasswordSuccess() {

        assertNotNull(encodedPassword);
        assertTrue(bCryptUtil.checkPassword(TEST_PASSWORD, encodedPassword));
    }

    @Test
    public void checkPasswordFail() {

        assertNotNull(encodedPassword);
        assertFalse(bCryptUtil.checkPassword("FakePassword", encodedPassword));
    }
}
