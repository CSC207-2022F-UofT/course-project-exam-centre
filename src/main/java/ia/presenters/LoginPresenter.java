package ia.presenters;

import uc.user.login.LoginFailedException;
import uc.user.login.LoginOutputBoundary;
import uc.user.login.LoginResponseModel;

public class LoginPresenter implements LoginOutputBoundary {
    @Override
    public LoginResponseModel prepareSuccessView(
            LoginResponseModel responseModel) {
        // TODO: Update view model here
        return responseModel;
    }

    @Override
    public LoginResponseModel prepareFailView(String errorMessage) {
        // TODO: Update view model here
        throw new LoginFailedException(errorMessage);
    }
}

