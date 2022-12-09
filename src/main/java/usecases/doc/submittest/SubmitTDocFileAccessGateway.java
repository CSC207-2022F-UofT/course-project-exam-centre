package usecases.doc.submittest;

/**
 * SubmitTDocFileAccessGateway provides methods for uploading the test document from the user's comptuer
 * @layer Use cases
 */
public interface SubmitTDocFileAccessGateway {

    /**
     * Uploads the test document into the app
     * @param dsRequestModel The request model containing the required information for document upload
     * @param docId Id of the document to be uploaded
     * @return Whether the request succeeds
     */
    boolean uploadTestDocument(SubmitTDocDsRequestModel dsRequestModel, String docId);
    
    /** Check connection status to FTP server
     * 
     * @return true if connection is successful, false otherwise
     */
    boolean checkConnectionStatus();
}
