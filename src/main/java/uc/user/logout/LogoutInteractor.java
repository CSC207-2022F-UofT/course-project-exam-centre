package uc.user.logout;

import entities.StateTracker;

/**
 * LogoutInteractor implements logout behaviour by removing currentUser
 * from the state tracker instance, and changing the view to a login screen.
 * @layer use cases
 */

public class LogoutInteractor implements LogoutInputBoundary {
    private LogoutOutputBoundary presenter;
    private StateTracker stateTracker;

    /**
     * Construct a LogoutInteractor object.
     * @param presenter has method to update the view to a login screen
     * @param stateTracker entity to be mutated by logOut method
     */
    public LogoutInteractor(LogoutOutputBoundary presenter, StateTracker stateTracker) {
        this.presenter = presenter;
        this.stateTracker = stateTracker;
    }

    /**
     * Remove the currentUser from the stateTracker instance and change the view to a login screen.
     */
    @Override
    public void logOut() {
        stateTracker.removeCurrentUser();
        presenter.prepareLoginView();
    }
}
