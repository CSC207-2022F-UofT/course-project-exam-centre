package uc.doc.submitsolution;

/**
 * SubmitSDocOutputBoundary provides methods for sending the status of the request back to the views
 * @layer Use cases
 */
public interface SubmitSDocOutputBoundary {

    /**
     * Alerts the Interface Adapter layer that the requested process succeeded
     * @param model Bundled data to inform the user of the success
     * @return The data to pass for presentation
     */
    SubmitSDocResponseModel prepareSuccessView(SubmitSDocResponseModel model);

    /**
     * Alerts the Interface Adapter layer that the request failed
     * @param error The error message
     * @return The data to pass for presentation
     */
    SubmitSDocResponseModel prepareFailureView(String error);

}
