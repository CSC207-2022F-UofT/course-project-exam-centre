package iadapters.exceptions;

/**
 * Exception for when the state tracker update has failed
 * @layer Interface adapters
 */
public class UpdateStateFailed extends RuntimeException{

    /**
     * Throws an error when updating the state of the entities failed
     * @param error
     */
    public UpdateStateFailed(String error){
        super(error);
    }
}
