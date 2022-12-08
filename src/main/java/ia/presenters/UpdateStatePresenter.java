package ia.presenters;

import ia.gateways.ViewManagerGateway;
import ia.exceptions.UpdateStateFailed;
import uc.state.update.UpdateStateOutputBoundary;
import uc.state.update.UpdateStateResponseModel;

public class UpdateStatePresenter implements UpdateStateOutputBoundary {

    private final ViewManagerGateway viewManagerGateway;

    /**
     * Creates a presenter for updating the view
     * @param viewManagerGateway Used for managing and updating views
     */
    public UpdateStatePresenter(ViewManagerGateway viewManagerGateway) {
        this.viewManagerGateway = viewManagerGateway;
    }

    /** Prepares FailView when the state is successfully updated.
     *
     * @param responseModel
     * @return responseModel corresponding to succesful update of state.
     */
    @Override
    public UpdateStateResponseModel prepareSuccessView(
            UpdateStateResponseModel responseModel){
        // TODO: Update view model
        return responseModel;
    }

    /** Prepares FailView when the state is unsuccessfully updated.
     *
     * @param errorMessage String of the error that occured.
     * @throws UpdateStateFailed when the state is not successfully updated.
     */
    @Override
    public UpdateStateResponseModel prepareFailView(String errorMessage){
        // TODO: Update view model
        viewManagerGateway.showError(errorMessage, "State Update Failed");
        throw new UpdateStateFailed(errorMessage);
    }

}
