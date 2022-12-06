package uc.state.update;

import entities.StateTracker;

/** UpdateStateRequestModel is a bundle of data that the UpdateStateInteractor can use
 * @layer use cases
 */
public class UpdateStateRequestModel {
    private StateTracker currentState;

    /**
     * Constructs an UpdateStateRequestModel that contains the currentState
     * @param currentState
     */
    public UpdateStateRequestModel(StateTracker currentState){
        this.currentState = currentState; // null if new StateTracker should be created
    }

    /**
     * Gets the current state
     * @return a StateTracker entity that contains the current state
     */
    public StateTracker getCurrentState() {
        return this.currentState;
    }
}
