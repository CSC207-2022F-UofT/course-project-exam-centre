package uc.user.login;

import entities.StateTracker;
import entities.User;
import entities.UserFactory;

/**
 * LoginInteractor implements login behaviour by adding user to
 * the stateTracker instance, and changing the view to the home screen
 * upon successful verification. Otherwise, failure popup is presented.
 * @layer use cases
 */
public class LoginInteractor implements LoginInputBoundary {
    private UserFactory userFactory;
    private LoginOutputBoundary presenter;
    private LoginDsGateway dsGateway;
    private StateTracker stateTracker;

    /**
     * Construct a LoginInteractor object.
     * @param userFactory creates User objects
     * @param presenter has method to update the view to login success/failure view
     * @param dsGateway has method to verify login credentials and get user information
     * @param stateTracker entity to be mutated by logIn method
     */
    public LoginInteractor(UserFactory userFactory, LoginOutputBoundary presenter,
                           LoginDsGateway dsGateway, StateTracker stateTracker) {
        this.userFactory = userFactory;
        this.presenter = presenter;
        this.dsGateway = dsGateway;
        this.stateTracker = stateTracker;
    }

    /**
     * Verify login credentials and add user as currentUser in stateTracker.
     * @param requestModel contains email and password via user input
     * @return LoginResponsesModel contains login status and userId
     */
    @Override
    public LoginResponseModel logIn(LoginRequestModel requestModel) {
        String email = requestModel.getEmail();
        String password = requestModel.getPassword();

        if (dsGateway.verifyLoginCredentials(email, password)) {
            LoginDsResponseModel dsResponseModel = dsGateway.getUserByEmail(email);
            String userId = dsResponseModel.getUserId();
            String firstName = dsResponseModel.getFirstName();
            String lastName = dsResponseModel.getLastName();
            User user = UserFactory.create(firstName, lastName, email, userId);
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
