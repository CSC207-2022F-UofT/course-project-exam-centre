package uc.user.login;

public class LoginInteractor implements LoginInputBoundary {

    private UserFactory userFactory;
    private LoginPresenter presenter;
    private LoginDsGateway dsGateway;

    public LoginInteractor(UserFactory userFactory, LoginPresenter presenter, LoginDsGateway dsGateway) {
        this.userFactory = userFactory;
        this.presenter = presenter;
        this.dsGateway = dsGateway;
    }

    @Override
    public LoginResponseModel logIn(LoginRequestModel requestModel) {
        return null;
    }
}
