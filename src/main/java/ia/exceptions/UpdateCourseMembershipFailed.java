package ia.exceptions;

/**
 * Exception for when updating a user's course membership has failed
 * @layer Interface adapters
 */
public class UpdateCourseMembershipFailed extends RuntimeException{

    /**
     * Throws an error when updating the course membership failed
     * @param errorMessage The error message
     */
    public UpdateCourseMembershipFailed(String errorMessage){
        super(errorMessage);
    }
}
