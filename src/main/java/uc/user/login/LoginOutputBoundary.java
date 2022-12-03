package uc.user.login;

/**
 * LoginOutputBoundary provides methods for the LoginInteractor.
 * They should be implemented by a presenter.
 * @layer use cases
 */
public interface LoginOutputBoundary {

    /**
     * Prepares the home screen.
     * @param responseModel contains loginStatus and userId
     */
    LoginResponseModel prepareSuccessView (LoginResponseModel responseModel);

    /**
     * Prepares a failure popup.
     * @param errorMessage to be displayed to user
     * @throws ia.presenters.LoginFailed runtime exception
     */
    LoginResponseModel prepareFailView (String errorMessage);
}
