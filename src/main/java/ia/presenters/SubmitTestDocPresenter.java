package ia.presenters;

import fworks.views.ViewManagerGateway;
import ia.exceptions.SubmitTestDocFailed;
import uc.doc.submittest.SubmitTDocOutputBoundary;
import uc.doc.submittest.SubmitTDocResponseModel;

public class SubmitTestDocPresenter implements SubmitTDocOutputBoundary {
    @Override
    public SubmitTDocResponseModel prepareSuccessView(
            SubmitTDocResponseModel responseModel) {
        // TODO: Update view model here
        return responseModel;
    }

    @Override
    public SubmitTDocResponseModel prepareFailureView(String errorMessage) {
        // TODO: Update view model here
        ViewManagerGateway.showError(errorMessage, "Test Document Submission Failed");
        throw new SubmitTestDocFailed(errorMessage);
    }
}

