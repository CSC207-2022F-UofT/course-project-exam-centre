package uc.doc.submittest;

/**
 * SubTDocFileAccessGateway provides methods for uploading the test document from the user's comptuer
 * @layer Use cases
 */
public interface SubTDocFileAccessGateway {

    /**
     * Uploads the test document into the app
     * @param dsRequestModel The request model containing the required information for document upload
     * @param docId The ID the document
     * @return Whether the request succeeds
     */
    boolean uploadTestDocument(SubTDocDsRequestModel dsRequestModel, String docId);
    
}
