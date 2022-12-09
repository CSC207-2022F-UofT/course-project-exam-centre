package usecases.user.register;
import java.util.List;
import java.util.LinkedList;

/** The URegisterRequestModel is responsible for formatting information for UserRegisterInteractor
 *  to use.
 * @layer use cases
 */
public class URegisterRequestModel{

    private String firstName;
    private String lastName;
    private String email;

    private String password;
    private String repeatPassword;

    private List<String> courses;

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
        this.courses = new LinkedList<>();
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

    /** Gets the email of the User
     *
     * @return returns the email of the User
     */
    String getEmail(){
        return email;
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

    /** Gets the list of courses that the User will enroll in
     *
     * @return returns a list of Strings containing the courses of the User
     */
    List<String> getCourses(){
        return courses;
    }
}
