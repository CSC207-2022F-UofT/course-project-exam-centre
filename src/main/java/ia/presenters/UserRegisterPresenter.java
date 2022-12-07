package ia.presenters;

import ia.exceptions.UserRegisterFailed;
import uc.user.register.URegisterOutputBoundary;
import uc.user.register.URegisterResponseModel;

public class UserRegisterPresenter implements URegisterOutputBoundary {
    /** Prepares the successView when the User is successfully registered
     *
     * @param responseModel responseModel representing newly-registerd user
     * @return response model representing newly-registerd user
     */
    @Override
    public URegisterResponseModel prepareSuccessView(URegisterResponseModel responseModel){
        // TODO: Update view model
        return responseModel;
    }

    /** Prepares a failView when the User is unsuccessfully registered
     *
     * @param errorMessage describes the error that occured when registering the User
     */
    @Override
    public URegisterResponseModel prepareFailView(String errorMessage){
        // TODO: Update view model
        throw new UserRegisterFailed(errorMessage);
    }

}
