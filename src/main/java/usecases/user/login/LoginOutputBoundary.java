package usecases.user.login;

public interface LoginOutputBoundary {
    LoginResponseModel prepareSuccessView (LoginResponseModel responseModel);
    LoginResponseModel prepareFailView (String errorMessage);

}
