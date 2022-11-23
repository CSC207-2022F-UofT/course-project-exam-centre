package fworks.views;

public class UserCreationFailed extends RuntimeException{
    public UserCreationFailed(String error){
        super(error);
    }
}
