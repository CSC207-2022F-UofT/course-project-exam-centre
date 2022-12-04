package ia.controllers;

import uc.user.login.LoginInputBoundary;
import uc.user.login.LoginRequestModel;
import uc.user.login.LoginResponseModel;

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
