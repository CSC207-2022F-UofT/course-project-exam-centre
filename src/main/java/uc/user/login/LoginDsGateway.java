package uc.user.login;

public interface LoginDsGateway {
    /** Return true if there is an email with corresponding password
     */
    boolean verifyLoginCredentials(String email, String password);
    LoginDsResponseModel getUserByEmail(String email);
}
