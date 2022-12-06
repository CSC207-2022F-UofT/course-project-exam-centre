package uc.doc.submitsolution;

/**
 * SubSDocOutputBoundary provides methods for sending the status of the request back to the views
 * @layer Use cases
 */
public interface SubSDocOutputBoundary {

    /**
     * Alerts the Interface Adapter layer that the requested process succeeded
     * @param model Bundled data to inform the user of the success
     * @return The data to pass for presentation
     */
    SubSDocResponseModel prepareSucessView(SubSDocResponseModel model);

    /**
     * Alerts the Interface Adapter layer that the request failed
     * @param error The error message
     * @return The data to pass for presentation
     */
    SubSDocRequestModel prepareFailureView(String error);

}
