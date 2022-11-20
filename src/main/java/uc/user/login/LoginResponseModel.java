package uc.user.login;

public class LoginResponseModel {
    private boolean loginStatus;
    private String userId;

    LoginResponseModel(boolean loginStatus, String userId) {

        this.loginStatus = loginStatus;
        this.userId = userId;
    }
}
