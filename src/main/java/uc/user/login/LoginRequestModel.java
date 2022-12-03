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

    /**
     * @return email inputted by user
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return password inputted by user
     */
    public String getPassword() {
        return password;
    }
}
