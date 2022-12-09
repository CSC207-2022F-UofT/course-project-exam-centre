package usecases.user.register;

/** The URegisterOutputBoundary provides methods for the UserRegisterInteractor to output data.
 * @layer use cases
 */
public interface URegisterOutputBoundary {
    /**
     * Prepares the home screen
     * @param user Response model that contains the user and the log-in status
     */
    URegisterResponseModel prepareSuccessView(URegisterResponseModel user);

    /**
     * Prepares a failure view
     * @param error The string of the error that occurred
     */
    URegisterResponseModel prepareFailView(String error);
}
