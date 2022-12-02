package ia.presenters;

public class FailedLogin extends RuntimeException {
    public FailedLogin(String errorMessage){
        super(errorMessage);
    }
}
