package usecases.user.logout;

import entities.StateTracker;

/**
 * LogoutInteractor implements logout behaviour.
 * @layer use cases
 */

public class LogoutInteractor implements LogoutInputBoundary {
    private LogoutOutputBoundary presenter;
    private StateTracker currentState;

    /**
     * Construct a LogoutInteractor object.
     * @param presenter has method to update the view to a login screen
     * @param currentState entity to be mutated by logOut method
     */
    public LogoutInteractor(LogoutOutputBoundary presenter, StateTracker currentState) {
        this.presenter = presenter;
        this.currentState = currentState;
    }

    /**
     * Remove the currentUser from the stateTracker instance.
     * Change the view to a login screen.
     */
    @Override
    public void logOut() {
        String currentUserId = currentState.getCurrentUser().getId();
        currentState.removeCurrentUser();
        presenter.prepareSuccessView(
                new LogoutResponseModel(
                        false,
                        currentUserId
                ));
    }
}
