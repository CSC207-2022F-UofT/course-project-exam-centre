package uc.state.update;

import entities.StateTracker;

public class UpdateStateRequestModel {
    private StateTracker currentState;

    public UpdateStateRequestModel(StateTracker currentState){
        this.currentState = currentState; // null if new StateTracker should be created
    }

    public StateTracker getCurrentState() {
        return this.currentState;
    }
}
