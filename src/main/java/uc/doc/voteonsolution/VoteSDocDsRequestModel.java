package uc.doc.voteonsolution;

/** VoteSDocDsRequestModel is a bundle of data to be stored as persistent data
 * @layer use cases
 */
public class  VoteSDocDsRequestModel {
    private final String solutionId;
    private final String userId;
    private final int vote;

    /** Creates an instance of VoteSDocDsRequest that contains a solutionId, userId and the vote total
     *
     * @param solutionId the Id of the solution that is being voted on
     * @param userId the userId of the user that is voting
     * @param vote the total number of votes on the solution
     */
    public VoteSDocDsRequestModel(String solutionId, String userId, int vote) {
        this.solutionId = solutionId;
        this.userId = userId;
        this.vote = vote;
    }

    /**
     * Gets the solutionId of the solution being voted on
     *
     * @return string of the solutionId
     */
    public String getSolutionId() {
        return this.solutionId;
    }

    /**
     * Gets the userId of the user that is voting
     *
     * @return string of the userId
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * Gets the total votes that the solution has
     *
     * @return the number of votes the solution has
     */
    public int getVote() {
        return this.vote;
    }
}
