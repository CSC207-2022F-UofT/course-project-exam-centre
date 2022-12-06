package uc.doc.submittest;

/**
 * SubmitTDocDsGateway provides methods to save the test document in persistent memory
 * @layer Use cases
 */
public interface SubmitTDocDsGateway {

    /**
     * Method used for saving the test document in memory
     * @param dsRequestModel The request model containing the required information to save a new test document
     * @return The newly created document's ID
     */
    String saveTestDocument(SubmitTDocDsRequestModel dsRequestModel);

}
