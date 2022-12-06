package ia.presenters;

import ia.exceptions.SubmitSolutionDocFailed;
import uc.doc.submitsolution.SubSDocOutputBoundary;
import uc.doc.submitsolution.SubSDocResponseModel;

public class SubmitSolutionDocPresenter implements SubSDocOutputBoundary {
    @Override
    public SubSDocResponseModel prepareSuccessView(
            SubSDocResponseModel responseModel) {
        // TODO: Update view model here
        return responseModel;
    }

    @Override
    public SubSDocResponseModel prepareFailView(String errorMessage) {
        // TODO: Update view model here
        throw new SubmitSolutionDocFailed(errorMessage);
    }
}

