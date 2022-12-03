package uc.user.logout;

import entities.StateTracker;

/**
 * LogoutInteractor implements logout behaviour.
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
     * Remove the currentUser from the stateTracker instance.
     * Change the view to a login screen.
     */
    @Override
    public void logOut() {
        stateTracker.removeCurrentUser();
        presenter.prepareLoginView();
    }
}
