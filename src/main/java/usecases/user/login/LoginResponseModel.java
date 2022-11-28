package usecases.user.login;

public class LoginResponseModel {
    private boolean loginStatus;
    private String userId;

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
