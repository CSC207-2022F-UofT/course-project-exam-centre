package usecases.user.login;

public interface LoginDsGateway {
    /** Return true if there is an email with corresponding password
     */
    boolean verifyLoginCredentials(String email, String password);
    LoginDsResponseModel getUserInfo(String email);
}
