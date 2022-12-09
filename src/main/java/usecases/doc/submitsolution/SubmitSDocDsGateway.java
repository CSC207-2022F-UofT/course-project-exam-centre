package usecases.doc.submitsolution;

import usecases.doc.submitsolution.dbmodels.SubmitSDocUserDbModel;

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

    /** Gets user data by user ID.
     *
     * @param userId the unique ID of the user being requested
     *
     * @return UserDbModel object representing the data for the
     * requested user entity
     */
    SubmitSDocUserDbModel getUserById(String userId);

    /** Checks whether gateway is connected to database.
     *
     * @return boolean representing whether database is connected
     */
    boolean getConnectionStatus();

}
