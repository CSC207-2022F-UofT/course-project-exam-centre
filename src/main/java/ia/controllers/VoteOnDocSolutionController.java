package ia.controllers;

import uc.doc.voteonsolution.VoteSDocInputBoundary;
import uc.doc.voteonsolution.VoteSDocRequestModel;
import uc.doc.voteonsolution.VoteSDocResponseModel;

public class VoteOnDocSolutionController {
    private final VoteSDocInputBoundary voteSDocInputBoundary;

    public VoteOnDocSolutionController(VoteSDocInputBoundary voteSDocInputBoundary) {
        this.voteSDocInputBoundary = voteSDocInputBoundary;
    }

    public VoteSDocResponseModel createInput(String solutionId, boolean vote){
        VoteSDocRequestModel voteSDocRequestModel = new VoteSDocRequestModel(solutionId, vote);
        return voteSDocInputBoundary.voteSolutionDoc(voteSDocRequestModel);
    }
    
}
