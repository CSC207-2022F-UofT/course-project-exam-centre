package ia.presenters;

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
        throw new SubmitDBMessageFailed(errorMessage);
    }
}

