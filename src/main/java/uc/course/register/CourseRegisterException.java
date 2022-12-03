package uc.course.register;

public class CourseRegisterException extends RuntimeException{
    public CourseRegisterException(String errorMessage){
        super(errorMessage);
    }
}
