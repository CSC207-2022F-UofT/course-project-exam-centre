package usecases.submessage;

/** SubDBMessOutputBoundary provides methods for the SubmitDBMessageInteractor to output data
 * @layer use cases
 */
public interface SubDBMessOutputBoundary {
    /**
     * Prepares a success view
     * @param message a responseModel containing the body, creation time, and parentId
     * @return SubDBMessResponseModel
     */
    SubDBMessResponseModel prepareSuccessView(SubDBMessResponseModel message);

    /**
     * Prepares a fail view
     * @param error String containing the error
     * @throws iadapters.exceptions.SubmitDBMessageFailed exception
     */
    SubDBMessResponseModel prepareFailView(String error);
}