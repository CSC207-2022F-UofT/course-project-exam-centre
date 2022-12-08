package uc.doc.downloaddoc;

/** DownloadDocResponseModel is a bundle of data that can be used by a presenter
 * @layer use cases
 */
public class DownloadDocResponseModel {
    private final String documentId;
    private final String downloadPath;

    /** Create an instance of DownloadDocResponseModel that contains the documentId and downloadPath
     * 
     * @param documentId
     * @param downloadPath
     */
    public DownloadDocResponseModel(String documentId, String downloadPath) {
        this.documentId = documentId;
        this.downloadPath = downloadPath;
    }

    /** Gets the document Id of the document that has been downloaded
     *
     * @return the documentId of the downloaded document
     */
    public String getDocumentId() {
        return this.documentId;
    }

    /** Gets the local file path where the document has been downloaded to
     *
     * @return the downloadPath of the document
     */
    public String getDownloadPath() {
        return this.downloadPath;
    }

}
