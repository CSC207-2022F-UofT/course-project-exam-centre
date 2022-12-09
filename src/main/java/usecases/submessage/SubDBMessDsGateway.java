package usecases.submessage;

import usecases.submessage.dbmodels.SubDBMessUserDbModel;

/** SubDBMessDsGateway provides methods to access, compare, and update persistent data
 * @layer use cases
 */
public interface SubDBMessDsGateway {

    /** Gets the TestID from the database when given the solutionID of the solution the user wants to add a Message to.
     *
     * @return returns the String type of the TestID that the solution is written for.
     */
    String getTestIdBySolutionId(String solutionId);

    /** Gets the CourseID from the database when given the TestID.
     *
     * @return returns the String type of the CourseID that the Test relates to.
     */
    String getCourseIdByTestId(String testId);

    /** adds the message to the database when given all the Message info through the request model
     *
     * @return returns the String Corresponding to the unique identifier for the message
     */
    String addMessage(SubDBMessDsRequestModel requestModel);

    /** Gets user data by user ID.
     *
     * @param userId the unique ID of the user being requested
     *
     * @return UserDbModel object representing the data for the
     * requested user entity
     */
    SubDBMessUserDbModel getUserById(String userId);

    /** Checks whether gateway is connected to database.
     *
     * @return boolean representing whether database is connected
     */
    boolean getConnectionStatus();
}
