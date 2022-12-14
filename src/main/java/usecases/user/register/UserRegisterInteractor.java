package usecases.user.register;

import entities.StateTracker;
import entities.User;
import entities.factories.UserFactory;

import java.time.LocalDateTime;

/** The UserRegisterInteractor implements the ability to register a user.
 * @layer use cases
 */
public class UserRegisterInteractor implements URegisterInputBoundary {

    private StateTracker currentState;
    private URegisterDsGateway userDsGateway;
    private URegisterOutputBoundary userOutputBoundary;
    private UserFactory userFactory;

    /** Creates an instance of the UserRegisterInteractor with its own DsGateway, Presenter, and Factory.
     *
     * @param userDsGateway use case gateway
     * @param userOutputBoundary use case output boundary
     * @param userFactory user entity factory
     */
    public UserRegisterInteractor(StateTracker currentState, URegisterDsGateway userDsGateway,
                                  URegisterOutputBoundary userOutputBoundary,
                                  UserFactory userFactory) {
        this.userDsGateway = userDsGateway;
        this.userOutputBoundary = userOutputBoundary;
        this.userFactory = userFactory;
        this.currentState = currentState;
    }

    /** Checks if a user's info is valid and saves the information if it is. Returns a Response Model and
     * failure or success views depending on whether a user is successfully created.
     *
     * @param requestModel The request model of the user containing all of their information
     * @return returns a response model that is dependent whether a user is created.
     */
    @Override
    public URegisterResponseModel registerUser(URegisterRequestModel requestModel){

        // Exception handling for failed db connection
        if (!userDsGateway.getConnectionStatus()) {
            return userOutputBoundary.prepareFailView("Database Connection Failed");
        }

        if(userDsGateway.checkIfUserExistsByEmail(requestModel.getEmail())){
            return userOutputBoundary.prepareFailView("User already exists");
        } else if(!(requestModel.getPassword().equals(requestModel.getRepeatPassword()))){
            return userOutputBoundary.prepareFailView("Passwords do not match");
        }

        URegisterDsRequestModel userDsModel = new URegisterDsRequestModel(
                requestModel.getEmail(),
                requestModel.getPassword(),
                requestModel.getFirstName(),
                requestModel.getLastName());

        String userId = userDsGateway.saveUser(userDsModel);

        User user = userFactory.create(requestModel.getFirstName(),
                requestModel.getLastName(), requestModel.getEmail(), userId);

        LocalDateTime now = LocalDateTime.now();

        // Set current user to newly-registered (log in user)
        currentState.setCurrentUser(user);

        URegisterResponseModel responseModel =new URegisterResponseModel(
                user.getId(),
                true
                );
        return userOutputBoundary.prepareSuccessView(responseModel);

    }
}
