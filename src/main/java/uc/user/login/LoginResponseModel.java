package uc.user.login;

/**
 * LoginResponseModel bundles data to be used by a presenter.
 */
public class LoginResponseModel {
    private String userId;
    private boolean loginStatus;

    /**
     * Construct a LoginResponseModel.
     * @param loginStatus is true if login is successful
     * @param userId the user's id
     */
    public LoginResponseModel(boolean loginStatus, String userId) {

        this.loginStatus = loginStatus;
        this.userId = userId;
    }

    /**
     * @return true if user successfully logged in
     */
    public boolean isLoggedIn() {
        return loginStatus;
    }

    /**
     * @return userId of logged-in user
     */
    public String getUserId() {
        return userId;
    }
}
