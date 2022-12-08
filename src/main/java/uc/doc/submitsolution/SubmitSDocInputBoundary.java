package uc.doc.submitsolution;

/**
 * SubmitSDocInputBoundary provides the method for storing documents in persistent data in the use case layer to the
 * interface adapter layer
 * @layer Use cases
 */
public interface SubmitSDocInputBoundary {

    /**
     * Runs the use case to submit a solution document
     * @param model A request model containing the needed information to store the document
     * @return The response model containing a timestamp, document name, and ID for the views to present
     */
    SubmitSDocResponseModel submitSolutionDoc(SubmitSDocRequestModel model);

}
