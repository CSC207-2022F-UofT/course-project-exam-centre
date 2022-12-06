package uc.doc.voteonsolution;

/** VoteSDocRequestModel is a bundle of information for the VoteOnSolutionDocInteractor to use
 * @layer use cases
 */
public class VoteSDocRequestModel {
    private final String solutionId;
    private final String userId;
    private final boolean vote;

    /** Creates an instance of VoteSDocRequestModel containing the ids of the solution and user voting as well as
     * the type of vote.
     * @param solutionId id of the solution being updated
     * @param userId the id of the user voting
     * @param vote whether it is an upvote or a downvote
     */
    public VoteSDocRequestModel(String solutionId, String userId, boolean vote) {
        this.solutionId = solutionId;
        this.userId = userId;
        this.vote = vote;
    }

    /** Get the solutionId of the solutionDoc being updated
     *
     * @return a string containing the solutionId
     */
    public String getSolutionId() {
        return this.solutionId;
    }

    /** Get the userID of the user voting
     *
     * @return a string containing the userId
     */
    public String getUserId() {
        return this.userId;
    }

    /** Get whether the solution is being up-voted or down-voted
     *
     * @return true if up-voted, false if down-voted
     */
    public boolean getVote() {
        return this.vote;
    }
}
