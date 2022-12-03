package uc.user.login;

public class LoginResponseModel {
    private String userId;

    public LoginResponseModel(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
