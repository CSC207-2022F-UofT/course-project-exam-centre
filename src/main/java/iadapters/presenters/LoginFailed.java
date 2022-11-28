package iadapters.presenters;

public class LoginFailed extends RuntimeException{
    public LoginFailed(String errorMessage){
        super(errorMessage);
    }
}
