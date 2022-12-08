package ia.exceptions;

public class UpdateStateFailed extends RuntimeException{
    public UpdateStateFailed(String error){
        super(error);
    }
}
