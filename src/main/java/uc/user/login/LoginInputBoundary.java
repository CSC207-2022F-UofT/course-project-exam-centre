package uc.user.login;

public interface LoginInputBoundary {
    /**
     * @param requestModel
     * @return a LoginResponseModel corresponding to the requestModel
     */
    LoginResponseModel logIn(LoginRequestModel requestModel);
}
