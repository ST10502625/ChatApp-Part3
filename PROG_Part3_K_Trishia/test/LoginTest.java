import org.junit.Test;
import static org.junit.Assert.*;

public class LoginTest {

    @Test
    public void testUsernameCorrectlyFormatted() {
        Login login = new Login();

        String result = login.registerUser(
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertEquals("User registered successfully!", result);
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {
        Login login = new Login();

        String result = login.registerUser(
                "kyle!!!!!!!",
                "Ch&&sec@ke99!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertEquals("Username is not correctly formatted.", result);
    }

    @Test
    public void testPasswordDoesNotMeetComplexity() {
        Login login = new Login();

        String result = login.registerUser(
                "kyl_1",
                "password",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertEquals("Password does not meet requirements.", result);
    }

    @Test
    public void testCellPhoneIncorrectlyFormatted() {
        Login login = new Login();

        String result = login.registerUser(
                "kyl_1",
                "Ch&&sec@ke99!",
                "08966553",
                "Kyle",
                "Smith"
        );

        assertEquals("Cell phone number incorrectly formatted.", result);
    }

    // ================= LOGIN TESTS =================

    @Test
    public void testLoginSuccessful() {
        Login login = new Login();

        login.registerUser(
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginFailed() {
        Login login = new Login();

        login.registerUser(
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertFalse(login.loginUser("kyl_1", "wrongpassword"));
    }

    // ================= BOOLEAN VALIDATION TESTS =================

    @Test
    public void testCheckUserName_Valid() {
        Login login = new Login();
        assertTrue(login.isUsernameValid("kyl_1"));
    }

    @Test
    public void testCheckUserName_Invalid() {
        Login login = new Login();
        assertFalse(login.isUsernameValid("kyle!!!!!!!"));
    }

    @Test
    public void testCheckPasswordComplexity_Valid() {
        Login login = new Login();
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testCheckPasswordComplexity_Invalid() {
        Login login = new Login();
        assertFalse(login.checkPasswordComplexity("password"));
    }

    @Test
    public void testCheckCellPhoneNumber_Valid() {
        Login login = new Login();
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testCheckCellPhoneNumber_Invalid() {
        Login login = new Login();
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }
}