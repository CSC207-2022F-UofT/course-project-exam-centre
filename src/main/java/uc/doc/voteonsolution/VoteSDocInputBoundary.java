package uc.doc.voteonsolution;

/** VoteSDocInputBoundary provides an entry point from the Interface Adapter layer to the Use Case layer for the
 * vote on solution use case
 * @layer use cases
 */
public interface VoteSDocInputBoundary {

    /**
     * Invokes the vote on solution use case
     * @param model a request model containing the solutionId, userId, and number of votes
     * @return VoteSDocResponseModel
     */
    VoteSDocResponseModel voteSolutionDoc(VoteSDocRequestModel model);

}
