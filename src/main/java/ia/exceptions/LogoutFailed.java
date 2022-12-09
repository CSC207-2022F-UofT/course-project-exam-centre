package ia.exceptions;

/**
 * Exception for when the user log out failed
 * @layer Interface adapters
 */
public class LogoutFailed extends RuntimeException {

    /**
     * Throws an error when the user logout failed
     * @param errorMessage The error message
     */
    public LogoutFailed(String errorMessage) {
    }
}
