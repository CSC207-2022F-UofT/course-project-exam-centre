package ia.controllers;

import uc.doc.voteonsolution.VoteSDocInputBoundary;
import uc.doc.voteonsolution.VoteSDocRequestModel;
import uc.doc.voteonsolution.VoteSDocResponseModel;

/**
 * VoteOnDocSolutionController provides an entry way into the VoteOnDocSolution use case
 * @layer interface adapters
 */
public class VoteOnDocSolutionController {
    private final VoteSDocInputBoundary voteSDocInputBoundary;

    /**
     * Constructs an instance of VoteOnDocSolutionController with an input boundary
     * @param voteSDocInputBoundary provides methods to invoke use case
     */
    public VoteOnDocSolutionController(VoteSDocInputBoundary voteSDocInputBoundary) {
        this.voteSDocInputBoundary = voteSDocInputBoundary;
    }

    /**
     * Invokes VoteOnDocSolution use case
     * @param solutionId id of the solution being voted on
     * @param vote true if upvote, false if downvote
     * @return a response model corresponding to the request model
     */
    public VoteSDocResponseModel createInput(String solutionId, boolean vote){
        VoteSDocRequestModel voteSDocRequestModel = new VoteSDocRequestModel(solutionId, vote);
        return voteSDocInputBoundary.voteSolutionDoc(voteSDocRequestModel);
    }
    
}
