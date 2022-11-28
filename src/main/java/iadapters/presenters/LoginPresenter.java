package iadapters.presenters;

import usecases.user.login.LoginOutputBoundary;
import usecases.user.login.LoginResponseModel;

public class LoginPresenter implements LoginOutputBoundary {
    @Override
    public LoginResponseModel prepareSuccessView(LoginResponseModel responseModel) {
        return responseModel;
    }

    @Override
    public LoginResponseModel prepareFailView(String errorMessage) {
        // Throw a custom exception
        throw new LoginFailed(errorMessage);
    }
}

