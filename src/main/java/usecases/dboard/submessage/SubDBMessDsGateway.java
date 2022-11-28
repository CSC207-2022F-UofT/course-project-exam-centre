package usecases.dboard.submessage;

// Use case layer

public interface SubDBMessDsGateway {

    /** Gets the TestID from the database when given the solutionID of the solution the user wants to add a Message to.
     *
     * @return returns the String type of the TestID that the solution is written for.
     */
    String getDBTestIDFromSolutionID(String solutionID);

    /** Gets the CourseID from the database when given the TestID.
     *
     * @return returns the String type of the CourseID that the Test relates to.
     */
    String getDBCourseIDFromTestID(String testID);

    /** adds the message to the database when given all the Message info through the request model
     *
     * @return returns the String Corresponding to the unique identifier for the message
     */
    String addMessageToDB(SubDBMessDsRequestModel requestModel);
}
