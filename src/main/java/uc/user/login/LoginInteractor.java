package uc.user.login;

import entities.StateTracker;
import entities.User;
import entities.factories.UserFactory;

/**
 * LoginInteractor implements login behaviour.
 * @layer use cases
 */
public class LoginInteractor implements LoginInputBoundary {
    private StateTracker stateTracker;
    private LoginDsGateway dsGateway;
    private LoginOutputBoundary outputBoundary;
    private UserFactory userFactory;

    /**
     * Construct a LoginInteractor object.
     * @param userFactory creates User objects
     * @param outputBoundary has method to update the view to login success/failure view
     * @param dsGateway has method to verify login credentials and get user information
     * @param stateTracker entity to be mutated by logIn method
     */
    public LoginInteractor(StateTracker stateTracker, LoginDsGateway dsGateway, LoginOutputBoundary outputBoundary,
                           UserFactory userFactory) {
        // TODO: Can we remove userFactory from constructor?
        this.stateTracker = stateTracker;
        this.dsGateway = dsGateway;
        this.outputBoundary = outputBoundary;
        this.userFactory = userFactory;
    }

    /**
     * Verify login credentials.
     * Add user as currentUser in stateTracker.
     * @param requestModel contains email and password via user input
     * @return LoginResponsesModel contains login status and userId
     */
    @Override
    public LoginResponseModel logIn(LoginRequestModel requestModel) {
        String email = requestModel.getEmail();
        String password = requestModel.getPassword();

        if (!dsGateway.verifyLoginCredentials(email, password)) {
            return outputBoundary.prepareFailView("Could not find a user with a matching email and password");
        } else {
            LoginDsResponseModel dsResponseModel = dsGateway.getUserByEmail(email);

            String userId = dsResponseModel.getUserId();
            String firstName = dsResponseModel.getFirstName();
            String lastName = dsResponseModel.getLastName();
            User user = userFactory.create(firstName, lastName, email, userId);
            stateTracker.setCurrentUser(user);

            LoginResponseModel responseModel = new LoginResponseModel(true, userId);
            return outputBoundary.prepareSuccessView(responseModel);
        }
    }
}
