package iadapters.controllers;

import entities.StateTracker;
import usecases.state.update.UpdateStateInputBoundary;
import usecases.state.update.UpdateStateRequestModel;
import usecases.state.update.UpdateStateResponseModel;

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
