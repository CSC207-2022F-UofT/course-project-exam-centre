package uc.user.login;

/**
 * LoginRequestModel contains user inputted data
 * to be manipulated by LoginInterator.
 * @layer use cases
 */
public class LoginRequestModel {
    private String email;
    private String password;

    /**
     * Construct a LoginRequestModel.
     * @param email inputted by user
     * @param password inputted by user
     */
    public LoginRequestModel(String email, String password){
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
