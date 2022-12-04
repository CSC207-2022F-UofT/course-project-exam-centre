package ia.exceptions;

public class SubmitTestDocFailed extends RuntimeException{
    public SubmitTestDocFailed(String error){
        super(error);
    }
}
