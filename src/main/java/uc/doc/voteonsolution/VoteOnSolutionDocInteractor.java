package uc.doc.voteonsolution;

import entities.Course;
import entities.SolutionDocument;
import entities.StateTracker;
import entities.TestDocument;
import entities.User;

/** VoteOnSolutionDocInteractor implements the ability to vote on a solution document
 * @layer use cases
 */
public class VoteOnSolutionDocInteractor implements VoteSDocInputBoundary {
    private final VoteSDocDsGateway voteSDocDsGateway;
    private final VoteSDocOutputBoundary voteSDocOutputBoundary;
    private final StateTracker stateTracker;

    /** Creates an instance of the VoteOnSolutionDocInteractor that contains a DsGateway, OutputBoundary and
     * state tracker.
     *
     * @param voteSDocDsGateway provides methods to access and update persistent data
     * @param voteSDocOutputBoundary provides methods to output data and update views
     * @param stateTracker tracks the state of entities accessed in the program
     */
    public VoteOnSolutionDocInteractor(VoteSDocDsGateway voteSDocDsGateway,
                                       VoteSDocOutputBoundary voteSDocOutputBoundary, StateTracker stateTracker) {
        this.voteSDocDsGateway = voteSDocDsGateway;
        this.voteSDocOutputBoundary = voteSDocOutputBoundary;
        this.stateTracker = stateTracker;
    }

    /** Takes in the needed information and updates the vote on a solution document
     * 
     * @param model The vote solution document model containing all the relevant information for updating
     * the vote on a solution document
     * @return If completed, the success view response model, containing information to be presented to the user
     */
    @Override
    public VoteSDocResponseModel voteSolutionDoc(VoteSDocRequestModel model) {

        // Exception handling for failed db connection
        if (!voteSDocDsGateway.getConnectionStatus()) {
            return voteSDocOutputBoundary.prepareFailView("Database Connection Failed");
        }

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
        
        // Update vote in Database
        voteSDocDsGateway.updateSolutionDocVote(voteSDocDsRequestModel);
        
        // Update vote in Solution Document
        String testId = voteSDocDsGateway.getTestIdBySolutionId(solutionId);
        String courseId = voteSDocDsGateway.getCourseIdByTestId(testId);
        Course course = stateTracker.getCourseIfTracked(courseId);

        TestDocument testDoc = course.getTest(testId);
        SolutionDocument solutionDoc = testDoc.getSolution(solutionId);
        solutionDoc.setVotes(voteTotal);

        VoteSDocResponseModel voteSDocResponseModel = new VoteSDocResponseModel(courseId, testId, solutionId, voteTotal);

        return voteSDocOutputBoundary.prepareSuccessView(voteSDocResponseModel);
    }
    
}
