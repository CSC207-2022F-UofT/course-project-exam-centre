package uc.doc.voteonsolution;

/** Provides methods for the VoteOnSolutionDocInteract to output data.
 * @layer use cases
 */
public interface VoteSDocOutputBoundary {

    /**
     * Prepares a success view
     * @param model Response model containing the vote total, sDoc and timestamp of the request
     */
    VoteSDocResponseModel prepareSuccessView(VoteSDocResponseModel model);

    /**
     * Prepares a failure view
     * @param errorMessage The errorMessage that occurs when a failure view happens
     */
    VoteSDocResponseModel prepareFailureView(String errorMessage);
    
}
