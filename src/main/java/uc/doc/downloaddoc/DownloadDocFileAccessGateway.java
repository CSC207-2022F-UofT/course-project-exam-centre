package uc.doc.downloaddoc;

/** Provides methods from the FileAccessGateway to access and update persistent data in the FTP server
 * @layer use cases
 */
public interface DownloadDocFileAccessGateway {

    /** Download document from FTP server to local machine using documentId
     * @param downloadDocFileAccessRequestModel     model containing documentId to be used for the download
     * @return the local file path of the downloaded file
     */
    String downloadDoc(DownloadDocFileAccessRequestModel downloadDocFileAccessRequestModel);

    /** Check connection status to FTP server
     * 
     * @return true if connection is successful, false otherwise
     */
    boolean checkConnectionStatus();
}
