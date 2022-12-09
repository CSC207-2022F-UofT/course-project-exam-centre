package ia.exceptions;

/**
 * Exception for when submitting a discussion board message failed
 * @layer Interface adapters
 */
public class SubmitDBMessageFailed extends RuntimeException{

    /**
     * Throws an exception when a discussion board message submission failed
     * @param error The error message
     */
    public SubmitDBMessageFailed(String error){
        super(error);
    }
}
