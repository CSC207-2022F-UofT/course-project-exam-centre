package ia.presenters;

import uc.user.login.LoginFailedException;
import uc.user.login.LoginOutputBoundary;
import uc.user.login.LoginResponseModel;

public class LoginPresenter implements LoginOutputBoundary {
    @Override
    public LoginResponseModel prepareSuccessView(LoginResponseModel responseModel) {
        return responseModel;
    }

    @Override
    public LoginResponseModel prepareFailView(String errorMessage) {
        // Throw a custom exception
        throw new LoginFailedException(errorMessage);
    }
}

