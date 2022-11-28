package iadapters.controllers;

import usecases.user.login.LoginInputBoundary;
import usecases.user.login.LoginRequestModel;
import usecases.user.login.LoginResponseModel;

public class LoginController {
    private final LoginInputBoundary inputBoundary;

    public LoginController(LoginInputBoundary inputBoundary){
        this.inputBoundary = inputBoundary;
    }

    public LoginResponseModel logIn(String email, String password) {
        LoginRequestModel requestModel = new LoginRequestModel(email, password);
        return inputBoundary.logIn(requestModel);
    }
}
