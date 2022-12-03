package uc.user.logout;

/**
 * LogoutOutputBoundary provides methods for the LogoutInteractor.
 * They should be implemented by a presenter.
 * @layer use cases
 */
public interface LogoutOutputBoundary {

    /**
     * Prepare a login view.
     */
    void prepareLoginView ();
}
