package iadapters.exceptions;

/**
 * Exception for when a document download from the server failed
 * @layer Interface adapters
 */
public class DownloadDocFailed extends RuntimeException{

    /**
     * Throws an error when the document download failed
     * @param error The error message
     */
    public DownloadDocFailed(String error){
        super(error);
    }
}
