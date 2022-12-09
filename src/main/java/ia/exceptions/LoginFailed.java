package ia.exceptions;

/**
 * Exception for when the user login fails
 * @layer Interface adapters
 */
public class LoginFailed extends RuntimeException {

    /**
     * Throws an exception when the user login failed
     * @param errorMessage The error message
     */
    public LoginFailed(String errorMessage) {
    }
}
