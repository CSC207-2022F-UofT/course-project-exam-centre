package ia.exceptions;

public class DownloadDocFailed extends RuntimeException{
    public DownloadDocFailed(String error){
        super(error);
    }
}
