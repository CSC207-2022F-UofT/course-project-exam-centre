package uc.state.update;

/** UpdateStateInputBoundary provides an entry way from the Interface Adapters layer to the Use Case layer for
 * the Update State use case
 * @layer use cases
 */
public interface UpdateStateInputBoundary {
    /**
     * Invokes the Update State use case
     * @param requestModel requestModel containing information about the current state
     * @return a responseModel corresponding to the requestModel
     */
    UpdateStateResponseModel updateState (UpdateStateRequestModel requestModel);
}
