package ia.exceptions;

public class SubmitDBMessageFailed extends RuntimeException{
    public SubmitDBMessageFailed(String error){
        super(error);
    }
}
