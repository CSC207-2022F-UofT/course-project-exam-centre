package uc.doc.voteonsolution;

import java.time.LocalDateTime;

import entities.SolutionDocument;
import entities.StateTracker;
import entities.User;

public class VoteOnSolutionDocInteractor implements VoteSDocInputBoundary {
    private final VoteSDocDsGateway voteSDocDsGateway;
    private final VoteSDocOutputBoundary voteSDocOutputBoundary;
    private final StateTracker stateTracker;
 
    public VoteOnSolutionDocInteractor(VoteSDocDsGateway voteSDocDsGateway, VoteSDocOutputBoundary voteSDocOutputBoundary, StateTracker stateTracker) {
        this.voteSDocDsGateway = voteSDocDsGateway;
        this.voteSDocOutputBoundary = voteSDocOutputBoundary;
        this.stateTracker = stateTracker;
    }

    @Override
    public VoteSDocResponseModel voteSolutionDoc(VoteSDocRequestModel model, SolutionDocument sDocument) {
        User user = stateTracker.getCurrentUser();
        boolean vote = model.getVote();
        String solutionId = model.getSolutionId();
        int voteTotal = voteSDocDsGateway.getVoteTotalBySolutionIdQuery(solutionId);
        
        if (vote){ // Upvote
            voteTotal += 1;
        } else { // Downvote
            voteTotal -= 1;
        }

        VoteSDocDsRequestModel voteSDocDsRequestModel = new VoteSDocDsRequestModel(solutionId, user.getId(), voteTotal);
        
        // Update vote in database
        voteSDocDsGateway.updateSolutionDocVote(voteSDocDsRequestModel);
        
        // Update vote in Solution Document
        sDocument.setVotes(voteTotal);

        VoteSDocResponseModel voteSDocResponseModel = new VoteSDocResponseModel(voteTotal, sDocument, LocalDateTime.now());

        return voteSDocOutputBoundary.prepareSuccessView(voteSDocResponseModel);
    }
    
}
