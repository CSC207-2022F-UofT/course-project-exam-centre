package ia.presenters;

import uc.user.login.LoginOutputBoundary;
import uc.user.login.LoginResponseModel;

public class LoginPresenter implements LoginOutputBoundary {
    /**
     * @param responseModel
     * @return a ResponseModel corresponding to successful log in
     */
    @Override
    public LoginResponseModel prepareSuccessView(LoginResponseModel responseModel) {
        return responseModel;
    }

    /**
     * @param errorMessage
     * @throws LoginFailed
     */
    @Override
    public LoginResponseModel prepareFailView(String errorMessage) {
        throw new LoginFailed(errorMessage);
    }
}

