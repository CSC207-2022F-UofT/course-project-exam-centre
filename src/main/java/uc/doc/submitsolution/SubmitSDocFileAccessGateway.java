package uc.doc.submitsolution;

/** SubmitSDocFileAccessGateway provides a method to access the file of the uploaded solution document
 * @layer Use cases
 */

public interface SubmitSDocFileAccessGateway {

    /**
     * Uploads the solution document into persistent data
     * @param model The DsRequest model containing the bundled data for storing the document
     * @param docId The ID of the document entity
     * @return Whether the request succeeded
     */
    boolean uploadSolutionDocument(SubmitSDocDsRequestModel model, String docId);

    /** Check connection status to FTP server
     * 
     * @return true if connection is successful, false otherwise
     */
    boolean checkConnectionStatus();
}
