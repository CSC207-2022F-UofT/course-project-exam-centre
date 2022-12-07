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

    /** Prepares the successView when the solution is successfully submitted.
     *
     * @param responseModel Bundled data to inform the user of the success
     * @return responseModel corresponding to successful submisison of solution.
     */
    @Override
    public SubmitSDocResponseModel prepareSuccessView(
            SubmitSDocResponseModel responseModel) {
        // TODO: Update view model here
        return responseModel;
    }

    /** Prepares the failView when the solution is unsuccessfully submitted.
     *
     * @param errorMessage A string containing the error message
     * @throws SubmitSolutionDocFailed when the submit solution doc use case fails.
     */
    @Override
    public SubmitSDocResponseModel prepareFailureView(String errorMessage) {
        // TODO: Update view model here
        viewManagerGateway.showError(errorMessage, "Solution Document Submission Failed");
        throw new SubmitSolutionDocFailed(errorMessage);
    }
}

