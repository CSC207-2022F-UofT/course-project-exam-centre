package usecases.submessage.responsemodels;

/** SubDBMessUserResponseModel is a bundle of data that can be used by a presenter
 * @layer use cases
 */
public class SubDBMessUserResponseModel {

    private final String userId;
    private final String email;
    private final String firstName;
    private final String lastName;

    /** Create an instance of SubDBMessUserResponseModel that contains the information of the user
     * that sends a message in the discussion board
     * 
     * @param userId        Id of the user that sent the message
     * @param email         email address of the user that sent the message
     * @param firstName     first name of the user that sent the message
     * @param lastName      last name of the user that sent the message
     */
    public SubDBMessUserResponseModel(String userId,
                                      String email,
                                      String firstName,
                                      String lastName) {
        this.userId = userId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /** Gets the Id of the user which sent the message
     *
     * @return the Id of the user
     */
    public String getUserId() {
        return this.userId;
    }

    /** Gets the email of the user which sent the message
     *
     * @return the email of the user
     */
    public String getEmail() {
        return this.email;
    }

    /** Gets the first name of the user which sent the message
     *
     * @return the first name of the user
     */
    public String getFirstName() {
        return this.firstName;
    }

    /** Gets the last name of the user which sent the message
     *
     * @return the last name of the user
     */
    public String getLastName() {
        return this.lastName;
    }

}
