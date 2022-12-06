package uc.user.register;

/** The URegisterDsRequestModel is responsible for bundling information
 *  that can then be stored as persistent data.
 * It contains information of the user that is attempting to register an account.
 * @layer use cases
 */
public class URegisterDsRequestModel {

    private String email;
    private String userId;
    private String password;
    private String firstName;
    private String lastName;

    /** Creates an instance of URegisterDsRequestModel containing an email, userId and password
     *
     * @param email the email that is being registered
     * @param password the password of the user registering
     */
    public URegisterDsRequestModel(String email,String password,
                                   String firstName, String lastName){
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /** Gets the email of the User being registered
     *
     * @return returns a string containing the email of the User
     */
    public String getEmail(){
        return this.email;
    }

    /** Gets the first name of the User being registered
     *
     * @return returns a string containing the firstname of the User
     */
    public String getFirstName(){
        return this.firstName;
    }

    /** Gets the last name of the User being registered
     *
     * @return returns a string containing the last name of the User
     */
    public String getLastName(){
        return this.lastName;
    }

    /** Gets the password of the User being registered
     *
     * @return returns a string containing the email of the User
     */
    public String getPassword(){
        return this.password;
    }
}
