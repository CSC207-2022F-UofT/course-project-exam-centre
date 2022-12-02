package uc.user.register;

import entities.User;
import entities.UserFactory;

import java.time.LocalDateTime;

public class UserRegisterInteractor implements URegisterInputBoundary {

    final URegisterDsGateway userDsGateway;
    final URegisterOutputBoundary userOutputBoundary;
    final UserFactory userFactory;

    /** Creates an instance of the UserRegisterInteractor with its own DsGateway, Presenter, and Factory.
     *
     * @param userDsGateway use case gateway
     * @param userOutputBoundary use case output boundary
     * @param userFactory user entity factory
     */
    public UserRegisterInteractor(URegisterDsGateway userDsGateway,
                                  URegisterOutputBoundary userOutputBoundary,
                                  UserFactory userFactory) {
        this.userDsGateway = userDsGateway;
        this.userOutputBoundary = userOutputBoundary;
        this.userFactory = userFactory;
    }

    /** Checks if a user's info is valid and saves the information if it is. Returns a Response Model and
     * failure or success views depending on whether a user is successfully created.
     *
     * @param requestModel The request model of the user containing all of their information
     * @return returns a response model that is dependent whether a user is created.
     */
    @Override
    public URegisterResponseModel registerUser(URegisterRequestModel requestModel){
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

        User user = UserFactory.create(userId, requestModel.getFirstName(),
                requestModel.getLastName(), requestModel.getEmail());

        LocalDateTime now = LocalDateTime.now();

        URegisterResponseModel responseModel =new URegisterResponseModel(
                user,
                true, // TODO: Update URegisterResponseModel
                now.toString());

        return userOutputBoundary.prepareSuccessView(responseModel);

    }
}
