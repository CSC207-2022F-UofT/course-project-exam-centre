package uc.user.register;

public class URegisterRequestModel {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String repeatPassword;

    /** Creates a URegisterRequestModel with a firstName, lastName,
     * email, password and the repeatPassword.
     *
     * @param firstName First name of the User
     * @param lastName Last name of the User
     * @param email Email of the User
     * @param password Password of the User
     * @param repeatPassword Repeated password of the User
     */
    public URegisterRequestModel(String firstName, String lastName,
                                 String email, String password, String repeatPassword){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    /** Gets the first name of the User
     *
     * @return returns the first name of the User
     */
    String getFirstName(){
        return firstName;
    }

    /** Gets the last name of the User
     *
     * @return returns the last name of the User
     */
    String getLastName(){
        return lastName;
    }

    /** Gets the password of the User
     *
     * @return returns the password name of the User
     */
    String getPassword(){
        return password;
    }

    /** Gets the repeated password of the User
     *
     * @return returns the repeated password of the User
     */
    String getRepeatPassword(){
        return repeatPassword;
    }
}
