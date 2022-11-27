package ia.controllers;

import entities.StateTracker;
import uc.state.update.UpdateStateInputBoundary;
import uc.state.update.UpdateStateRequestModel;
import uc.state.update.UpdateStateResponseModel;

public class UpdateStateController {
    private final UpdateStateInputBoundary inputBoundary;

    public UpdateStateController(UpdateStateInputBoundary inputBoundary){
        this.inputBoundary = inputBoundary;
    }

    public UpdateStateResponseModel updateState(StateTracker currentState) {
        UpdateStateRequestModel requestModel = new UpdateStateRequestModel(currentState);
        return inputBoundary.updateState(requestModel);
    }
}
