package ia.presenters;

import fworks.views.ViewManagerGateway;
import ia.exceptions.SubmitDBMessageFailed;
import uc.dboard.submessage.SubDBMessOutputBoundary;
import uc.dboard.submessage.SubDBMessResponseModel;

public class SubmitDBMessagePresenter implements SubDBMessOutputBoundary {
    @Override
    public SubDBMessResponseModel prepareSuccessView(
            SubDBMessResponseModel responseModel) {
        // TODO: Update view model here
        return responseModel;
    }

    @Override
    public SubDBMessResponseModel prepareFailView(String errorMessage) {
        // TODO: Update view model here
        ViewManagerGateway.showError(errorMessage, "Discussion Board Message Submission Failed");
        throw new SubmitDBMessageFailed(errorMessage);
    }
}

