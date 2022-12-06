package uc.doc.submitsolution;

/** SubSDocFileAccessGateway provides a method to access the file of the uploaded solution document
 * @layer Use cases
 */

public interface SubSDocFileAccessGateway {

    /**
     * Uploads the solution document into persistent data
     * @param model The DsRequest model containing the bundled data for storing the document
     * @param docId The ID of the document entity
     * @return Whether the request succeeded
     */
    boolean uploadSolutionDocument(SubSDocDsRequestModel model, String docId);

}
