package usecases.user.login;

/**
 * LoginOutputBoundary provides methods for an interactor.
 * They should be implemented by a presenter.
 * @layer use cases
 */
public interface LoginOutputBoundary {
    /**
     * Prepares the home screen.
     * @param responseModel contains loginStatus and userId
     * @return a ResponseModel corresponding to successful log in
     */
    LoginResponseModel prepareSuccessView(LoginResponseModel responseModel);

    /**
     * Prepares a failure popup.
     * @param errorMessage to be displayed to user
     * @throws RuntimeException runtime exception
     */
    LoginResponseModel prepareFailView(String errorMessage);
}
