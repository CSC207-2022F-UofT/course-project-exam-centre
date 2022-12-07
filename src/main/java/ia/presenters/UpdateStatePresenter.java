package ia.presenters;

import fworks.views.ViewManagerGateway;
import ia.exceptions.UpdateStateFailed;
import uc.state.update.UpdateStateOutputBoundary;
import uc.state.update.UpdateStateResponseModel;

public class UpdateStatePresenter implements UpdateStateOutputBoundary {

    @Override
    public UpdateStateResponseModel prepareSuccessView(
            UpdateStateResponseModel responseModel){
        // TODO: Update view model
        return responseModel;
    }

    @Override
    public UpdateStateResponseModel prepareFailView(String errorMessage){
        // TODO: Update view model
        ViewManagerGateway.showError(errorMessage, "State Update Failed");
        throw new UpdateStateFailed(errorMessage);
    }

}
