package ia.presenters;

import fworks.views.ViewManagerGateway;
import ia.exceptions.UserRegisterFailed;
import uc.user.register.URegisterOutputBoundary;
import uc.user.register.URegisterResponseModel;

public class UserRegisterPresenter implements URegisterOutputBoundary {

    private final ViewManagerGateway viewManagerGateway;

    /**
     * Creates a presenter for updating the view
     * @param viewManagerGateway Used for managing and updating views
     */
    public UserRegisterPresenter(ViewManagerGateway viewManagerGateway) {
        this.viewManagerGateway = viewManagerGateway;
    }


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
     * @throws UserRegisterFailed when the user register use case fails.
     */
    @Override
    public URegisterResponseModel prepareFailView(String errorMessage){
        // TODO: Update view model
        viewManagerGateway.showError(errorMessage, "User Registration Failed");
        throw new UserRegisterFailed(errorMessage);
    }

}
