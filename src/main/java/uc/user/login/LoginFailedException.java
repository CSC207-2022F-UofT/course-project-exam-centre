package uc.user.login;

public class LoginFailedException extends RuntimeException{
    public LoginFailedException(String errorMessage){
        super(errorMessage);
    }
}
