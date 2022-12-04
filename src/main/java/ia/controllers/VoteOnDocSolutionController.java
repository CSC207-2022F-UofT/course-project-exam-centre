package ia.controllers;

import entities.SolutionDocument;
import uc.doc.voteonsolution.VoteSDocInputBoundary;
import uc.doc.voteonsolution.VoteSDocRequestModel;
import uc.doc.voteonsolution.VoteSDocResponseModel;

public class VoteOnDocSolutionController {
    private final VoteSDocInputBoundary voteSDocInputBoundary;

    public VoteOnDocSolutionController(VoteSDocInputBoundary voteSDocInputBoundary) {
        this.voteSDocInputBoundary = voteSDocInputBoundary;
    }

    public VoteSDocResponseModel createInput(String solutionId, String userId, boolean vote, SolutionDocument sDocument){
        VoteSDocRequestModel voteSDocRequestModel = new VoteSDocRequestModel(solutionId, userId, vote);
        return voteSDocInputBoundary.voteSolutionDoc(voteSDocRequestModel, sDocument);
    }
    
}
