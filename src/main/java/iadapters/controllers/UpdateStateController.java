package iadapters.controllers;

import usecases.state.update.UpdateStateInputBoundary;
import usecases.state.update.UpdateStateResponseModel;

/**
 * UpdateStateController provides an entry way into the UpdateState use case
 * @layer interface adapters
 */
public class UpdateStateController {
    private final UpdateStateInputBoundary inputBoundary;

    /**
     * Constructs an instance of UpdateStateController with an input boundary
     * @param inputBoundary provides methods to invoke use case
     */
    public UpdateStateController(UpdateStateInputBoundary inputBoundary){
        this.inputBoundary = inputBoundary;
    }

    /**
     * Invokes the UpdateState use case
     * @return a response model corresponding to the request model
     */
    public UpdateStateResponseModel updateState() {
        return inputBoundary.updateState();
    }
}
