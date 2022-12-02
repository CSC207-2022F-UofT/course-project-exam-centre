package uc.user.login;

/**
 * Methods defined in LoginDsGateway are used by LoginInteractor
 * to access persistent data.
 * @layer use cases
 */
public interface LoginDsGateway {
    /**
     * @param email inputted by user
     * @param password inputted by user
     * @return true if there is an email with corresponding email
     */
    boolean verifyLoginCredentials(String email, String password);

    /**
     *
     * @param email
     * @return
     */
    LoginDsResponseModel getUserByEmail(String email);
}
