package uc.user.login;

public interface LoginOutputBoundary {
    public LoginResponseModel prepareSuccessView (LoginResponseModel responseModel);
    public LoginResponseModel prepareFailView (LoginResponseModel responseModel);
}
