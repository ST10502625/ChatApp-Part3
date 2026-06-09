import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Login {

    // Instance variables
    private String username;
    private String password;
    private String cellPhoneNumber;
    private String firstName;
    private String lastName;

    // Default constructor
    public Login() {}

    /**
     * Validates username: must contain underscore and be <= 5 characters
     * @param username
     * @return 
     */
    public boolean isUsernameValid(String username) {
        return username != null &&
               username.contains("_") &&
               username.length() <= 5;
    }

    /**
     * Checks password complexity: min 8 chars, uppercase, number, special char
     * @param password
     * @return 
     */
    public boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }

        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasCapital = true;
            } else if (Character.isDigit(c)) {
                hasNumber = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
            }
        }
        return hasCapital && hasNumber && hasSpecial;
    }

    /**
     * Validates cell phone number (must start with +27 followed by 9 digits)
     * @param cell
     * @return 
     */
    public boolean checkCellPhoneNumber(String cell) {
        if (cell == null) return false;
           // Allow either:
          // - +27 followed by 9 digits (international format)
         // - 0 followed by 9 digits (local South African format)
            String regex = "^(\\+27|0)\\d{9}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(cell);
            return matcher.matches();
       }

    /**
     * Registers user after full validation.
     * Returns short error messages matching test expectations.
     * @param username
     * @param password
     * @param cellPhone
     * @param firstName
     * @param lastName
     * @return 
     */
    public String registerUser(String username, String password,
                               String cellPhone, String firstName, String lastName) {

        if (!isUsernameValid(username)) {
            return "Username is not correctly formatted.";
        }

        if (!checkPasswordComplexity(password)) {
            return "Password does not meet requirements.";
        }

        if (!checkCellPhoneNumber(cellPhone)) {
            return "Cell phone number incorrectly formatted.";
        }

        // All validations passed - store user details
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhone;
        this.firstName = firstName;
        this.lastName = lastName;

        return "User registered successfully!";
    }

    /**
     * Verifies login credentials
     * @param username
     * @param password
     * @return 
     */
    public boolean loginUser(String username, String password) {
        if (this.username == null || this.password == null) {
            return false;
        }
        return this.username.equals(username) && this.password.equals(password);
    }

    /**
     * Returns login status message
     * @param status
     * @return 
     */
    public String returnLoginStatus(boolean status) {
        if (status) {
            return "Welcome " + this.firstName + " " + this.lastName +
                   ", it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    // Getters
    public String getUsername() { return username; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getCellPhoneNumber() { return cellPhoneNumber; }
}
