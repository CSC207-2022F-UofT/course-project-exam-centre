package ia.controllers;

import uc.user.register.URegisterInputBoundary;
import uc.user.register.URegisterRequestModel;
import uc.user.register.URegisterResponseModel;

/**
 *  UserRegisterController provides an entry way into the UserRegister use case
 * @layer interface adapters
 */
public class UserRegisterController {

    URegisterInputBoundary userInput;

    /** Constructs a UserRegisterController with an instance of a URegisterInputBoundary
     *
     * @param userInput The input boundaries of the user register system
     */
    public UserRegisterController(URegisterInputBoundary userInput){
        this.userInput = userInput;
    }

    /** Calls the register new user method in the user register interactor
     *
     * @param firstName the first name of the user
     * @param lastName the last name of the user
     * @param email the email of the user
     * @param password1 the password of the user
     * @param password2 the repeated password of the user
     * @return returns a URegiseterResponseModel containing the data of the User
     */
    public URegisterResponseModel registerUser(
            String firstName,
            String lastName,
            String email,
            String password1,
            String password2) {

        URegisterRequestModel requestModel = new URegisterRequestModel(firstName,
                lastName, email, password1, password2);

        return userInput.registerUser(requestModel);
    }
}
