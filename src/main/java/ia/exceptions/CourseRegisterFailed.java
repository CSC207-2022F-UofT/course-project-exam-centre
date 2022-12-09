package ia.exceptions;

/**
 * Exception for when creating a new course failed
 * @layer Interface adapters
 */
public class CourseRegisterFailed extends RuntimeException{
    /**
     * Raises an error when creating a new course failed
     * @param errorMessage The error message
     */
    public CourseRegisterFailed(String errorMessage){
        super(errorMessage);
    }
}
