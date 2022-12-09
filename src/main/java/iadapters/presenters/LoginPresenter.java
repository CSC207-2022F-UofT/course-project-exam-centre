package iadapters.presenters;

import iadapters.gateways.ViewManagerGateway;
import iadapters.exceptions.LoginFailed;
import iadapters.viewmodels.MainViewModel;
import usecases.user.login.LoginOutputBoundary;
import usecases.user.login.LoginResponseModel;

/**
 * LoginPresenter updates the views after the Login use case
 * @layer interface adapters
 */
public class LoginPresenter implements LoginOutputBoundary {

    private final ViewManagerGateway viewManagerGateway;
    private final MainViewModel viewModel;

    /**
     * Creates a presenter for updating the view
     * @param viewManagerGateway Used for managing and updating views
     */
    public LoginPresenter(
            ViewManagerGateway viewManagerGateway, MainViewModel viewModel) {
        this.viewManagerGateway = viewManagerGateway;
        this.viewModel = viewModel;
    }

    /** Prepares SuccessView after successful Login
     * @param responseModel the response model for the login use case
     * @return a ResponseModel corresponding to successful log in
     */
    @Override
    public LoginResponseModel prepareSuccessView(LoginResponseModel responseModel) {
        viewManagerGateway.updateState();
        viewManagerGateway.updateViews();
        return responseModel;
    }

    /** Prepares FailView after unsuccessful Login
     *
     * @param errorMessage an error message describing the use case failure
     * @throws LoginFailed occurs when the login use case fails
     */
    @Override
    public LoginResponseModel prepareFailView(String errorMessage) {
        throw new LoginFailed(errorMessage);
    }
}

