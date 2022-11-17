package ia.presenters;

import fworks.views.UserCreationFailed;
import uc.user.register.URegisterOutputBoundary;
import uc.user.register.URegisterResponseModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class URegisterPresenter implements URegisterOutputBoundary {
    /** Prepares the successView when the User is successfully registered
     *
     * @param user the user that is being registered
     * @return returns a success view if the User was successfully registered
     */
    @Override
    public URegisterResponseModel prepareSuccessView(URegisterResponseModel user){
        LocalDateTime responseTime = LocalDateTime.parse(user.getTimestamp());
        user.setTimestamp(responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));
        return user;
    }

    /** Prepares a failView when the User is unsuccessfully registered
     *
     * @param error The error that occurs when registering the User
     * @return throws an exception containing the string of the error
     */
    @Override
    public URegisterResponseModel prepareFailView(String error){
        throw new UserCreationFailed(error);
    }

}
