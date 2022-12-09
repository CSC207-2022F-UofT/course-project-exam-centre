package usecases.doc.submitsolution.responsemodels;

/** SubmitSDocUserResponseModel is a bundle of data that can be used by a presenter
 * @layer use cases
 */
public class SubmitSDocUserResponseModel {

    private final String userId;
    private final String email;
    private final String firstName;
    private final String lastName;

    /** Create an instance of SubmitSDocUserResponseModel that contains information regarding the user
     * which submitted the solution document
     * 
     * @param userId            Id of the user that submitted the solution
     * @param email             Email of the user that submitted the solution
     * @param firstName         First name of the user that submitted the solution
     * @param lastName          Last name of the user that submitted the solution
     */
    public SubmitSDocUserResponseModel(String userId,
                                       String email,
                                       String firstName,
                                       String lastName) {
        this.userId = userId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /** Gets the Id of the user that submitted the solution
     *
     * @return the Id of the user 
     */
    public String getUserId() {
        return this.userId;
    }

    /** Gets the email of the user that submitted the solution
     *
     * @return the email of the user 
     */
    public String getEmail() {
        return this.email;
    }

    /** Gets the first name of the user that submitted the solution
     *
     * @return the first name of the user 
     */
    public String getFirstName() {
        return this.firstName;
    }

    /** Gets the last name of the user that submitted the solution
     *
     * @return the last name of the user 
     */
    public String getLastName() {
        return this.lastName;
    }

}
