package uc.user.login;

public interface LoginDsGateway {
    /**
     * @param email
     * @param password
     * @return true iff there is a user with a matching email and password
     */
    boolean authenticate(String email, String password);

    /**
     * @param email
     * @return a LoginDsResponseModel corresponding to the email
     */
    LoginDsResponseModel getUser(String email);
}
