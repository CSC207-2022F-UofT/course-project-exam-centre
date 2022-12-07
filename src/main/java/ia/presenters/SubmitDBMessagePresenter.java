package ia.presenters;

import fworks.views.ViewManagerGateway;
import ia.exceptions.SubmitDBMessageFailed;
import uc.dboard.submessage.SubDBMessOutputBoundary;
import uc.dboard.submessage.SubDBMessResponseModel;

public class SubmitDBMessagePresenter implements SubDBMessOutputBoundary {

    private final ViewManagerGateway viewManagerGateway;

    /**
     * Creates a presenter for updating the view
     * @param viewManagerGateway Used for managing and updating views
     */
    public SubmitDBMessagePresenter(ViewManagerGateway viewManagerGateway) {
        this.viewManagerGateway = viewManagerGateway;
    }

    @Override
    public SubDBMessResponseModel prepareSuccessView(
            SubDBMessResponseModel responseModel) {
        // TODO: Update view model here
        return responseModel;
    }

    @Override
    public SubDBMessResponseModel prepareFailView(String errorMessage) {
        // TODO: Update view model here
        viewManagerGateway.showError(errorMessage, "Discussion Board Message Submission Failed");
        throw new SubmitDBMessageFailed(errorMessage);
    }
}

