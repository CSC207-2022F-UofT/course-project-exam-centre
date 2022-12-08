package uc.doc.downloaddoc;

/** DownloadDocFileAccessRequestModel is a bundle of data to be stored as persistent data
 * @layer use cases
 */
public class DownloadDocFileAccessRequestModel {
    private final String documentId;
    private final String userId;

    /** Creates an instance of DownloadDocFileAccessRequestModel
     * 
     * @param documentId        the name of document to be downloaded
     * @param userId            the user which is requesting the download
     */
    public DownloadDocFileAccessRequestModel(String documentId, String userId) {
        this.documentId = documentId;
        this.userId = userId;
    }

    public String getDocumentId() {
        return this.documentId;
    }

    public String getUserId() {
        return this.userId;
    }

}
