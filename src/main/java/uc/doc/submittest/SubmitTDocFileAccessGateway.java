package uc.doc.submittest;

/**
 * SubmitTDocFileAccessGateway provides methods for uploading the test document from the user's comptuer
 * @layer Use cases
 */
public interface SubmitTDocFileAccessGateway {

    /**
     * Uploads the test document into the app
     * @param dsRequestModel The request model containing the required information for document upload
     * @param docId The ID the document
     * @return Whether the request succeeds
     */
    boolean uploadTestDocument(SubmitTDocDsRequestModel dsRequestModel, String docId);
    
}
