package uc.user.login;

/**
 * Methods defined in LoginOutputBoundary are used by LoginInteractor
 * to either prepare the view to the home screen,
 * or prepare a failure popup, which are implemented by a presenter.
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
     * @throws ia.presenters.LoginFailed exception
     */
    LoginResponseModel prepareFailView (String errorMessage);
}
