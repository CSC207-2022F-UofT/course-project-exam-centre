package ia.controllers;

import uc.state.update.UpdateStateInputBoundary;
import uc.state.update.UpdateStateResponseModel;

public class UpdateStateController {
    private final UpdateStateInputBoundary inputBoundary;

    public UpdateStateController(UpdateStateInputBoundary inputBoundary){
        this.inputBoundary = inputBoundary;
    }

    public UpdateStateResponseModel updateState() {
        return inputBoundary.updateState();
    }
}
