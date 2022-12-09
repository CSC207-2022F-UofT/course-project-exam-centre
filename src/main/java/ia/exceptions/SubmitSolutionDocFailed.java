package ia.exceptions;

/**
 * Exception for when submitting and saving a solution document failed
 */
public class SubmitSolutionDocFailed extends RuntimeException{

    /**
     * Throws an exception when the solutino doc submission failed
     * @param error The error message
     */
    public SubmitSolutionDocFailed(String error){
        super(error);
    }
}
