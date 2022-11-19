package uc.user.login;

import entities.UserFactory;

public class LoginInteractor implements LoginInputBoundary {
    private LoginOutputBoundary presenter;
    private LoginDsGateway dsGateway;

    public LoginInteractor(LoginOutputBoundary presenter, LoginDsGateway dsGateway) {
        this.presenter = presenter;
        this.dsGateway = dsGateway;
    }

    @Override
    public LoginResponseModel logIn(LoginRequestModel requestModel) {

        if (dsGateway.verifyLoginCredentials(requestModel.getEmail(), requestModel.getPassword())) {
            String userId = dsGateway.getUserId(requestModel.getEmail());
            LoginResponseModel responseModel = new LoginResponseModel(true, userId);
            presenter.prepareSuccessView(responseModel);
        } else {
            presenter.prepareFailView("The password entered is either incorrect " +
                    "or the email entered is not associated with an account.");
        }
        return null;
    }

}
