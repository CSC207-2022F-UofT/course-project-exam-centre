package uc.user.login;

/**
 * Methods defined in LoginOutputBoundary are used by LoginInteractor
 * to either change the view to the home screen,
 * or present a failure popup, which are implemented by a presenter.
 * @layer use cases
 */
public interface LoginOutputBoundary {
    LoginResponseModel prepareSuccessView (LoginResponseModel responseModel);
    LoginResponseModel prepareFailView (String errorMessage);
}
