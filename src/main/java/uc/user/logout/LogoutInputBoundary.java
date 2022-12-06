package uc.user.logout;

/**
 * LogoutOutputBoundary provides an entry-point to the logout use case.
 * Used by a Controller and implemented by LogoutInteractor.
 * @layer use cases
 */
public interface LogoutInputBoundary {

    /**
     * Invoke logout use case.
     */
    void logOut ();
}
