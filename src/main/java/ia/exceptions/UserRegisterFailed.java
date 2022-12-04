package ia.exceptions;

public class UserRegisterFailed extends RuntimeException{
    public UserRegisterFailed(String error){
        super(error);
    }
}
