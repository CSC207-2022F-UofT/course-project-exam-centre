package ia.presenters;

import ia.exceptions.SubmitTestDocFailed;
import uc.doc.submittest.SubTDocOutputBoundary;
import uc.doc.submittest.SubTDocResponseModel;

public class SubmitTestDocPresenter implements SubTDocOutputBoundary {
    @Override
    public SubTDocResponseModel prepareSuccessView(
            SubTDocResponseModel responseModel) {
        // TODO: Update view model here
        return responseModel;
    }

    @Override
    public SubTDocResponseModel prepareFailView(String errorMessage) {
        // TODO: Update view model here
        throw new SubmitTestDocFailed(errorMessage);
    }
}

