package uc.user.login;

import entities.StateTracker;
import entities.User;
import entities.UserFactory;

public class LoginInteractor implements LoginInputBoundary {
    private StateTracker stateTracker;
    private LoginDsGateway dsGateway;
    private LoginOutputBoundary outputBoundary;
    private UserFactory userFactory;

    public LoginInteractor(StateTracker stateTracker, LoginDsGateway dsGateway, LoginOutputBoundary outputBoundary,
                           UserFactory userFactory) {
        this.stateTracker = stateTracker;
        this.dsGateway = dsGateway;
        this.outputBoundary = outputBoundary;
        this.userFactory = userFactory;
    }

    /**
     * Verify login credentials and track current user
     * @param requestModel contains email and password via user input
     * @return LoginResponsesModel contains userId
     */
    @Override
    public LoginResponseModel logIn(LoginRequestModel requestModel) {
        String email = requestModel.getEmail();
        String password = requestModel.getPassword();

        if (!dsGateway.authenticate(email, password)) {
            return outputBoundary.prepareFailView("Could not find a user with a matching email and password");
        } else {
            LoginDsResponseModel dsResponseModel = dsGateway.getUser(email);

            String userId = dsResponseModel.getUserId();
            String firstName = dsResponseModel.getFirstName();
            String lastName = dsResponseModel.getLastName();
            User user = UserFactory.create(firstName, lastName, email, userId);
            stateTracker.setCurrentUser(user);

            LoginResponseModel responseModel = new LoginResponseModel(userId);
            return outputBoundary.prepareSuccessView(responseModel);
        }
    }
}
