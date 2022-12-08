package ia.presenters;

import ia.gateways.ViewManagerGateway;
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

    /** Prepares the successView when the message is successfully submitted.
     *
     * @param responseModel a responseModel containing the body, creation time, and parentId
     * @return responseModel corresponding to successful submission of message.
     */
    @Override
    public SubDBMessResponseModel prepareSuccessView(
            SubDBMessResponseModel responseModel) {
        // TODO: Update view model here
        return responseModel;
    }

    /** Prepares the failView when the message is unsuccessfully submitted.
     *
     * @param errorMessage String containing the error
     * @throws SubmitDBMessageFailed when the submit discussion message use case fails.
     */
    @Override
    public SubDBMessResponseModel prepareFailView(String errorMessage) {
        // TODO: Update view model here
        viewManagerGateway.showError(errorMessage, "Discussion Board Message Submission Failed");
        throw new SubmitDBMessageFailed(errorMessage);
    }
}

