package uc.user.logout;

/**
 * LogoutResponseModel bundles data to be used by a presenter.
 * @layer use cases
 */
public class LogoutResponseModel {
    private final boolean loginStatus;
    private final String userId;

    /**
     * Construct a LogoutResponseModel.
     * @param loginStatus is true if login is successful
     * @param userId the user's id
     */
    public LogoutResponseModel(boolean loginStatus, String userId) {

        this.loginStatus = loginStatus;
        this.userId = userId;
    }

    /**
     * @return true if user successfully logged in
     */
    public boolean checkLoginStatus() {
        return loginStatus;
    }

    /**
     * @return userId of logged-in user
     */
    public String getUserId() {
        return userId;
    }
}
