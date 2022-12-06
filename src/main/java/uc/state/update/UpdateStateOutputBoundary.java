package uc.state.update;

/** UpdateStateOutputBoundary provides methods for the UpdateStateInteractor to output data
 * @layer use cases
 */
public interface UpdateStateOutputBoundary {
    /**
     * Prepares a success view
     * @param responseModel the responseModel containing the user, the courses and the courseInfo
     */
    UpdateStateResponseModel prepareSuccessView (UpdateStateResponseModel responseModel);

    /**
     * Prepares a fail view
     * @param errorMessage A string detailing the error that had occurred
     * @return ... TODO: create exception for this thingy? or would it be all the exceptions
     */
    UpdateStateResponseModel prepareFailView (String errorMessage);

}
