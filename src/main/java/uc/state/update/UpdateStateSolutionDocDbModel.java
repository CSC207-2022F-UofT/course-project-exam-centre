package uc.state.update;

/** UpdateStateSolutionDocDbModel provides TODO: finish this
 * @layer use cases
 */
public interface UpdateStateSolutionDocDbModel {
    /**
     * TODO: Finish
     * @return a string containing a solutionId
     */
    String getSolutionId();

    /**
     * TODO: Finish
     * @return a string containing the solution name
     */
    String getSolutionName();

    /**
     * TODO: Finish
     * @return a string containing a testId
     */
    String getTestId();

    /**
     * TODO: Finish
     * @return a string containing a userId
     */
    String getUserId();

    /**
     * TODO: Finish
     * @return an integer representing the vote total of a solution
     */
    Integer getVoteTotal();

    /**
     * TODO: Finish
     * @return a float representing the recorded score of a user on a test
     */
    Float getRecordedScore();

    /**
     * TODO: Finish
     * @return a float representing the estimated time it took to complete a test
     */
    Float getEstimatedTime();

    /**
     * TODO: Finish
     * @return a String containing the rootMessageId
     */
    String getRootMessageId();
}
