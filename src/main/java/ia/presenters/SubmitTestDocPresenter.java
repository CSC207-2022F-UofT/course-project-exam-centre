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

    /** Prepares the successView when the test document is successfully submitted.
     *
     * @param responseModel A response model containing the information to be passed to the presenters for a success
     * @return resposeModel corresponding to successful submission of test document
     */
    @Override
    public SubmitTDocResponseModel prepareSuccessView(
            SubmitTDocResponseModel responseModel) {
        // TODO: Update view model here
        return responseModel;
    }

    /** Prepares the successView when the test document is unsuccessfully submitted.
     *
     * @param errorMessage The error that occurred
     * @throws SubmitTestDocFailed when the submit solution doc use case fails.
     */
    @Override
    public SubmitTDocResponseModel prepareFailureView(String errorMessage) {
        // TODO: Update view model here
        viewManagerGateway.showError(errorMessage, "Test Document Submission Failed");
        throw new SubmitTestDocFailed(errorMessage);
    }
}

