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

}
