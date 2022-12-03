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
     * @return true if inputted password matches stored password associated with email
     */
    boolean verifyLoginCredentials(String email, String password);

    /**
     * Get user information associated with the email.
     * @param email inputted by user
     * @return LoginDsResponseModel which contains user's first and last name, id, and email
     */
    LoginDsResponseModel getUserByEmail(String email);
}
