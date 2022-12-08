package uc.doc.downloaddoc;

/** DownloadDocRequestModel is a bundle of information for the DownloadDocInteractor to use
 * @layer use cases
 */
public class DownloadDocRequestModel {
    private final String documentId;
    private final String userId;

    /** Creates an instance of DownloadDocRequestModel containing the name of the document to download
     *  and the Id of the user requesting the download
     * @param documentId        the name of the document to be download
     * @param userId            the id of the user requesting the download
     */
    public DownloadDocRequestModel(String documentId, String userId) {
        this.documentId = documentId;
        this.userId = userId;
    }

    /** Get the documentId of the document to be downloaded
     *
     * @return a string containing the documentId
     */
    public String getDocumentId() {
        return this.documentId;
    }

    /** Get the userId of the user requesting the download
     *
     * @return a string containing the userId
     */
    public String getUserId() {
        return this.userId;
    }

}
