package ia.presenters;

import ia.gateways.ViewManagerGateway;
import ia.exceptions.LogoutFailed;
import uc.user.logout.LogoutOutputBoundary;
import uc.user.logout.LogoutResponseModel;

public class LogoutPresenter implements LogoutOutputBoundary {

    private final ViewManagerGateway viewManagerGateway;

    /**
     * Creates a presenter for updating the view
     * @param viewManagerGateway Used for managing and updating views
     */
    public LogoutPresenter(ViewManagerGateway viewManagerGateway) {
        this.viewManagerGateway = viewManagerGateway;
    }

    @Override
    public LogoutResponseModel prepareSuccessView(LogoutResponseModel responseModel) {
        // TODO: Update view model here
        return responseModel;
    }

    /** Prepares failView when logout is unsucessful.
     *
     * @param errorMessage String displaying the error that occured.
     * @throws LogoutFailed when the logout use case fails.
     */
    @Override
    public LogoutResponseModel prepareFailView(String errorMessage) {
        // TODO: Update view model here
        viewManagerGateway.showError(errorMessage, "Logout Failed");
        throw new LogoutFailed(errorMessage);
    }
}

