package iadapters.controllers;

import usecases.user.login.LoginInputBoundary;
import usecases.user.login.LoginRequestModel;
import usecases.user.login.LoginResponseModel;

/**
 * LoginController provides an entry way into the Login use case
 * @layer interface adapters
 */
public class LoginController {
    private final LoginInputBoundary inputBoundary;

    public LoginController(LoginInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    /**
     * @param email the email for the user login attempt
     * @param password the plaintext password for the user login attempt
     * @return the LoginResponseModel
     */
    public LoginResponseModel logIn(String email, String password) {
        LoginRequestModel requestModel = new LoginRequestModel(email, password);
        return inputBoundary.logIn(requestModel);
    }
}
