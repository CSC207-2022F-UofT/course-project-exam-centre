package uc.doc.submitsolution;

/**
 * SubmitSDocDsGateway provides methods to save document, and save/modify the root message of the message board
 * @layer Use cases
 */

public interface SubmitSDocDsGateway {

    /**
     * Saves the solution document in memory
     * @param model The request model containing the needed information to save the solution document
     * @return The ID of the newly saved document
     */
    String saveSolutionDocument(SubmitSDocDsRequestModel model);

    /**
     * Adds a root message to the solution document
     * @param solutionId The ID of the solution document
     * @param userId The ID of the user posting the message
     * @return The ID of the message
     */
    String addRootMessage(String solutionId,
                          String userId);

    /**
     * Updates the root message on the solution document
     * @param solutionId The ID of the solution document
     * @param rootMessageId The ID of the root message to modify
     */
    void updateRootMessageIdOfSolution(
            String solutionId,
            String rootMessageId);

}
