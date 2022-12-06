package uc.dboard.submessage;

/** SubDBMessOutputBoundary provides methods for the SubmitDBMessageInteractor to output data
 * @layer use cases
 */
public interface SubDBMessOutputBoundary {
    /**
     * Prepares a success view
     * @param message a responseModel containing the body, creation time, and parentId
     * @return
     */
    SubDBMessResponseModel prepareSuccessView(SubDBMessResponseModel message);

    /**
     * Prepares a fail view
     * @param error String containing the error
     * @throws ia.exceptions.SubmitDBMessageFailed exception
     */
    SubDBMessResponseModel prepareFailView(String error);
}