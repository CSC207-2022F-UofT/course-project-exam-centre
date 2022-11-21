package uc.user.register;

public class URegisterDsRequestModel {

    private String email;

    private String userId;

    private String password;

    /** Creates an instance of URegisterDsRequestModel containing an email, userId and password
     *
     * @param email the email that is being registered
     * @param userId the userId of the user registering
     * @param password the password of the user registering
     */
    public URegisterDsRequestModel(String email, String userId, String password){
        this.email = email;
        this.userId = userId;
        this.password = password;
    }

    /** Gets the email of the User being registered
     *
     * @return returns a string containing the email of the User
     */
    public String getEmail(){
        return this.email;
    }

    /** Gets the userId of the User being registered
     *
     * @return returns a string containing the email of the User
     */
    public String getUserId(){
        return this.userId;
    }

    /** Gets the password of the User being registered
     *
     * @return returns a string containing the email of the User
     */
    public String getPassword(){
        return this.password;
    }
}
