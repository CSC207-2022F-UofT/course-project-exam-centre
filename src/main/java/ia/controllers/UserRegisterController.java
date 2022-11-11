package ia.controllers;

import uc.user.register.URegisterInputBoundary;
import uc.user.register.URegisterRequestModel;
import uc.user.register.URegisterResponseModel;

public class UserRegisterController {

    URegisterInputBoundary userInput;

    public UserRegisterController(URegisterInputBoundary userInput){
        this.userInput = userInput;
    }

    URegisterResponseModel create(String firstName, String lastName, String email,
                                  String password1, String password2) {

        URegisterRequestModel requestModel = new URegisterRequestModel(firstName,
                lastName, email, password1, password2);

        return userInput.create(requestModel);
    }
}
