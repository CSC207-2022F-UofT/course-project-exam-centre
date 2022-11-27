package uc.user.login;

public interface LoginDsGateway {
    boolean verifyLoginCredentials(String email, String password);
    LoginDsResponseModel getUserInfo(String email);
}
