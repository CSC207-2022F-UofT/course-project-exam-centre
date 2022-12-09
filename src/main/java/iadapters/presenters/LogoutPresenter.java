package iadapters.presenters;

import iadapters.gateways.ViewManagerGateway;
import iadapters.exceptions.LogoutFailed;
import iadapters.viewmodels.MainViewModel;
import usecases.user.logout.LogoutOutputBoundary;
import usecases.user.logout.LogoutResponseModel;

/**
 * LogoutPresenter updates the views after the Logout use case
 * @layer interface adapters
 */
public class LogoutPresenter implements LogoutOutputBoundary {

    private final ViewManagerGateway viewManagerGateway;
    private final MainViewModel viewModel;

    /**
     * Creates a presenter for updating the view
     * @param viewManagerGateway Used for managing and updating views
     */
    public LogoutPresenter(
            ViewManagerGateway viewManagerGateway, MainViewModel viewModel) {
        this.viewManagerGateway = viewManagerGateway;
        this.viewModel = viewModel;
    }

    @Override
    public LogoutResponseModel prepareSuccessView(LogoutResponseModel responseModel) {
        viewModel.reset();
        viewManagerGateway.updateViews();
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

