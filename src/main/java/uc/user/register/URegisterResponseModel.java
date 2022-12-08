package uc.user.register;

/** The URegisterResponseModel is responsible for formatting information into a way the presenter can use.
 * @layer use cases
 */
public class URegisterResponseModel {
    private final String userId;
    private final Boolean loginStatus;

    /**
     * Creates a URegisterResponseModel with the login and the creationTime it took to create the User
     *
     * @param userId      the User ID corresponding to the URegisterResponseModel
     * @param loginStatus the login details of the User
     */
    public URegisterResponseModel(String userId, Boolean loginStatus) {
        this.userId = userId;
        this.loginStatus = loginStatus;
    }

    /**
     * Get the User ID
     *
     * @return returns the User ID related to the user being registered
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * Get the login of the User
     *
     * @return returns the login of the User
     */
    public Boolean getLoginStatus() {
        return loginStatus;
    }
}