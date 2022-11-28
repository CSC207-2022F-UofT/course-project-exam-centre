package usecases.user.register;

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

    /** Creates an 8-digit number that can be assigned to a User. If the randomly generated UserId is already
     * assigned to a User, another UserId will be generated and returned. This repeats until a new UserId is
     * generated.
     *
     * @return returns an 8-digit number in String form to assign to a User
     */
    private String createUserId(){
        int max = 99999999;
        int min = 0;
        int randomNum = (int) Math.floor(Math.random() * (max - min + 1)) + min;
        String randomKey = String.format("%08d", randomNum);

        if(!(userDsGateway.checkIfUserExists(randomKey))){
            return randomKey;
        } else{
            return createUserId();
        }
        
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

        User user = userFactory.create(requestModel.getFirstName(),
                requestModel.getLastName(), requestModel.getEmail(), createUserId());

        LocalDateTime now = LocalDateTime.now();
        URegisterDsRequestModel userDsModel = new URegisterDsRequestModel(user.getEmail(), user.getId(),
                requestModel.getPassword());

        boolean registerStatus = userDsGateway.saveNewUserInfo(userDsModel);

        URegisterResponseModel responseModel =new URegisterResponseModel(user, String.valueOf(registerStatus),
                now.toString());

        return userOutputBoundary.prepareSuccessView(responseModel);

    }
}
