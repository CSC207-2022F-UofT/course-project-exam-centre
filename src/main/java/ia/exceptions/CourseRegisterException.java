package ia.exceptions;

public class CourseRegisterException extends RuntimeException{
    public CourseRegisterException(String errorMessage){
        super(errorMessage);
    }
}
