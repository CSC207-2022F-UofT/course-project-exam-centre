package iadapters.exceptions;

/**
 * Exception for when submitting or saving a solution document failed
 * @layer Interface adapters
 */
public class SubmitTestDocFailed extends RuntimeException{

    /**
     * Throws an error when the test document submission failed
     * @param error The error message
     */
    public SubmitTestDocFailed(String error){
        super(error);
    }
}
