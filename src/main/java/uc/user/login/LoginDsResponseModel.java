package uc.user.login;

public class LoginDsResponseModel {
    /** Contains user information required in constructing a user object
     */
    private String userId;
    private String firstName;
    private String lastName;

    public LoginDsResponseModel(String userId, String firstName, String lastName){

        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
