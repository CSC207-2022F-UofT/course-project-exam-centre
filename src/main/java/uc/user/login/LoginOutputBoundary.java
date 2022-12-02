package uc.user.login;

public interface LoginOutputBoundary {
    /**
     * @param responseModel
     * @return the ResponseModel corresponding to successful log in
     */
    LoginResponseModel prepareSuccessView(LoginResponseModel responseModel);

    /**
     * @param errorMessage
     * @return the ResponseModel corresponding to the error
     */
    LoginResponseModel prepareFailView(String errorMessage);
}
