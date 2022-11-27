package uc.user.login;

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
            return presenter.prepareSuccessView(responseModel);
        } else {
            return presenter.prepareFailView("The password entered is either incorrect " +
                    "or the email entered is not associated with an account.");
        }
    }

}
