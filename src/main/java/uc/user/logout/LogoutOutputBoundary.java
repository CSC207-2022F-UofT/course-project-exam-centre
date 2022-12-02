package uc.user.logout;

/**
 * Method defined in LoginOutputBoundary is used by LoginInteractor
 * to change the view to the login view, which is implemented by a presenter
 * @layer use cases
 */
public interface LogoutOutputBoundary {
    void prepareLoginView ();
}
