package ia.presenters;

import fworks.views.ViewManagerGateway;
import ia.exceptions.LoginFailed;
import uc.user.login.LoginOutputBoundary;
import uc.user.login.LoginResponseModel;

public class LoginPresenter implements LoginOutputBoundary {

    private final ViewManagerGateway viewManagerGateway;

    /**
     * Creates a presenter for updating the view
     * @param viewManagerGateway Used for managing and updating views
     */
    public LoginPresenter(ViewManagerGateway viewManagerGateway) {
        this.viewManagerGateway = viewManagerGateway;
    }

    /**
     * @param responseModel the response model for the login use case
     * @return a ResponseModel corresponding to successful log in
     */
    @Override
    public LoginResponseModel prepareSuccessView(LoginResponseModel responseModel) {
        return responseModel;
    }

    /**
     * @param errorMessage an error message describing the use case failure
     * @throws LoginFailed occurs when the login use case fails
     */
    @Override
    public LoginResponseModel prepareFailView(String errorMessage) {
        viewManagerGateway.showError(errorMessage, "Login Failed");
        throw new LoginFailed(errorMessage);
    }
}

