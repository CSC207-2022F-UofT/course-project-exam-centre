package ia.exceptions;

public class UpdateCourseMembershipFailed extends RuntimeException{
    public UpdateCourseMembershipFailed(String errorMessage){
        super(errorMessage);
    }
}
