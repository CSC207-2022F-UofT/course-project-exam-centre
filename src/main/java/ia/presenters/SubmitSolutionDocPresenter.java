package ia.presenters;

import fworks.views.ViewManagerGateway;
import ia.exceptions.SubmitSolutionDocFailed;
import uc.doc.submitsolution.SubmitSDocOutputBoundary;
import uc.doc.submitsolution.SubmitSDocResponseModel;

public class SubmitSolutionDocPresenter implements SubmitSDocOutputBoundary {
    @Override
    public SubmitSDocResponseModel prepareSuccessView(
            SubmitSDocResponseModel responseModel) {
        // TODO: Update view model here
        return responseModel;
    }

    @Override
    public SubmitSDocResponseModel prepareFailureView(String errorMessage) {
        // TODO: Update view model here
        ViewManagerGateway.showError(errorMessage, "Solution Document Submission Failed");
        throw new SubmitSolutionDocFailed(errorMessage);
    }
}

