package uc.user.login;

/**
 * LoginResponseModel contains data to be used by a presenter
 * in order to switch the view to a home screen.
 */
public class LoginResponseModel {
    private boolean loginStatus;
    private String userId;

    /**
     * Construct a LoginResponseModel.
     * @param loginStatus is true if login is successful
     * @param userId the user's id
     */
    public LoginResponseModel(boolean loginStatus, String userId) {

        this.loginStatus = loginStatus;
        this.userId = userId;
    }

    public boolean isLoggedIn() {
        return loginStatus;
    }

    public String getUserId() {
        return userId;
    }
}
