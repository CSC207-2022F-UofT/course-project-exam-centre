package ia.exceptions;

/**
 * Exception for when registering a user has failed
 * @layer Interface adapters
 */
public class UserRegisterFailed extends RuntimeException{

    /**
     * Throws an exception when regestering a new user failed
     * @param error The error message
     */
    public UserRegisterFailed(String error){
        super(error);
    }
}
