package ia.presenters;

import fworks.views.ViewManagerGateway;
import ia.exceptions.SubmitTestDocFailed;
import uc.doc.submittest.SubmitTDocOutputBoundary;
import uc.doc.submittest.SubmitTDocResponseModel;

public class SubmitTestDocPresenter implements SubmitTDocOutputBoundary {

    private final ViewManagerGateway viewManagerGateway;

    /**
     * Creates a presenter for updating the view
     * @param viewManagerGateway Used for managing and updating views
     */
    public SubmitTestDocPresenter(ViewManagerGateway viewManagerGateway) {
        this.viewManagerGateway = viewManagerGateway;
    }

    @Override
    public SubmitTDocResponseModel prepareSuccessView(
            SubmitTDocResponseModel responseModel) {
        // TODO: Update view model here
        return responseModel;
    }

    @Override
    public SubmitTDocResponseModel prepareFailureView(String errorMessage) {
        // TODO: Update view model here
        viewManagerGateway.showError(errorMessage, "Test Document Submission Failed");
        throw new SubmitTestDocFailed(errorMessage);
    }
}

