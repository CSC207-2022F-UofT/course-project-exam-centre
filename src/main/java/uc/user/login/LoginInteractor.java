package uc.user.login;

import entities.StateTracker;
import entities.User;
import entities.UserFactory;

public class LoginInteractor implements LoginInputBoundary {
    private UserFactory userFactory;
    private LoginOutputBoundary presenter;
    private LoginDsGateway dsGateway;
    private StateTracker stateTracker;

    public LoginInteractor(UserFactory userFactory, LoginOutputBoundary presenter,
                           LoginDsGateway dsGateway, StateTracker stateTracker) {
        this.userFactory = userFactory;
        this.presenter = presenter;
        this.dsGateway = dsGateway;
        this.stateTracker = stateTracker;
    }

    /** Verify login credentials and track current user
     * @param requestModel contains email and password via user input
     * @return LoginResponsesModel contains login status and userId.
     */
    @Override
    public LoginResponseModel logIn(LoginRequestModel requestModel) {
        String email = requestModel.getEmail();
        String password = requestModel.getPassword();

        if (dsGateway.verifyLoginCredentials(email, password)) {
            LoginDsResponseModel dsResponseModel = dsGateway.getUserInfo(email);
            String userId = dsResponseModel.getUserId();
            String firstName = dsResponseModel.getFirstName();
            String lastName = dsResponseModel.getLastName();
            User user = userFactory.create(firstName, lastName, email, userId);
            stateTracker.setCurrentUser(user);

            // change view to log in screen
            LoginResponseModel responseModel = new LoginResponseModel(true, userId);
            return presenter.prepareSuccessView(responseModel);
        } else {
            // prepare error message
            return presenter.prepareFailView("The password entered is either incorrect " +
                    "or the email entered is not associated with an account.");
        }
    }

}
