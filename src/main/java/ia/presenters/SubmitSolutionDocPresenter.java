package ia.presenters;

import fworks.views.ViewManagerGateway;
import ia.exceptions.SubmitSolutionDocFailed;
import uc.doc.submitsolution.SubmitSDocOutputBoundary;
import uc.doc.submitsolution.SubmitSDocResponseModel;

public class SubmitSolutionDocPresenter implements SubmitSDocOutputBoundary {

    private final ViewManagerGateway viewManagerGateway;

    /**
     * Creates a presenter for updating the view
     * @param viewManagerGateway Used for managing and updating views
     */
    public SubmitSolutionDocPresenter(ViewManagerGateway viewManagerGateway) {
        this.viewManagerGateway = viewManagerGateway;
    }

    @Override
    public SubmitSDocResponseModel prepareSuccessView(
            SubmitSDocResponseModel responseModel) {
        // TODO: Update view model here
        return responseModel;
    }

    @Override
    public SubmitSDocResponseModel prepareFailureView(String errorMessage) {
        // TODO: Update view model here
        viewManagerGateway.showError(errorMessage, "Solution Document Submission Failed");
        throw new SubmitSolutionDocFailed(errorMessage);
    }
}

