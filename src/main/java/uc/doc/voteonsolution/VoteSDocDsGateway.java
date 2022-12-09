package uc.doc.voteonsolution;

/** Provides methods to access and update persistent data
 * @layer use cases
 */
public interface VoteSDocDsGateway {

    /**
     * Check the vote total using the solutionId
     * @param solutionId the solutionId of the solution of interest
     * @return the vote total
     */
    int getVoteTotalBySolutionIdQuery(String solutionId);

    /**
     * Update the solutionDoc's vote count in persistent data
     * @param model the requestModel containing the user and solution Ids as well as the number of votes
     * @return whether the request was processed
     */
    boolean updateSolutionDocVote(VoteSDocDsRequestModel model);

    /** Gets the TestId from the database when given the solutionId of the solution the user wants to add a Message to.
     * @param testId Id of the test document we are using to find its corresponding course Id
     * @return returns the String type of the TestId that the solution is written for.
     */
    String getTestIdBySolutionId(String solutionId);

    /** Gets the CourseId from the database when given the TestId.
     * @param solutionId Id of the solution document we are using to find its corresponding test Id
     * @return returns the String type of the CourseId that the Test relates to.
     */
    String getCourseIdByTestId(String testId);

    /** Checks whether gateway is connected to database.
     *
     * @return boolean representing whether database is connected
     */
    boolean getConnectionStatus();
    
}
