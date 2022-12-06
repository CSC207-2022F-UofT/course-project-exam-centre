package ia.exceptions;

public class CourseRegisterFailed extends RuntimeException{
    public CourseRegisterFailed(String errorMessage){
        super(errorMessage);
    }
}
