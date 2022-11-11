package ia.presenters;

import uc.user.register.URegisterResponseModel;

public interface URegisterPresenter {
    /** Decides whether or not a Successview should be shown if the User was successfully registered
     *
     * @param user the user that is being registered
     * @return returns a success view if the User was successfully registered
     */
    URegisterResponseModel prepareSuccessView(URegisterResponseModel user);


    URegisterResponseModel prepareFailView(String error);

}
