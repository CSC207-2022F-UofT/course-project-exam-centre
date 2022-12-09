package iadapters.exceptions;

/**
 * Exception for when voting on a solution document has failed
 * @layer Interface adapters
 */
public class VoteSDocFailed extends RuntimeException{

    /**
     * Throws an error when voting on a solution document failed
     * @param error The error message
     */
    public VoteSDocFailed(String error){
        super(error);
    }
}
