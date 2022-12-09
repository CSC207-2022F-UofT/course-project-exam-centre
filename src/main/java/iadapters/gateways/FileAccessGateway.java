package iadapters.gateways;

import usecases.doc.downloaddoc.DownloadDocFileAccessGateway;
import usecases.doc.downloaddoc.DownloadDocFileAccessRequestModel;
import usecases.doc.submitsolution.SubmitSDocDsRequestModel;
import usecases.doc.submitsolution.SubmitSDocFileAccessGateway;
import usecases.doc.submittest.SubmitTDocDsRequestModel;
import usecases.doc.submittest.SubmitTDocFileAccessGateway;

public interface FileAccessGateway extends SubmitSDocFileAccessGateway, SubmitTDocFileAccessGateway, DownloadDocFileAccessGateway {
    
    /** Upload a file to the FTP server
     *
     * @param filePath         file path of local file
     * @param docId            file name
     * @return returns true if file upload is successful, false otherwise
     */
    boolean uploadFile(String filePath, String docId);

    /** Upload a solution document to the FTP server
     * 
     * @param model         request model containing information on the document to be uploaded
     * @param docId         Id of document to be uploaded
     * @return returns true if file upload is successful, false otherwise
     */
    default boolean uploadSolutionDocument(SubmitSDocDsRequestModel model, String docId) {
        String filePath = model.getFilePath();
        return uploadFile(filePath, docId);
    }

    /** Upload a test document to the FTP server
     * 
     * @param dsRequestModel    request model containing information on the document to be uploaded
     * @param docId             Id of document to be uploaded
     * @return returns true if file upload is successful, false otherwise
     */
    default boolean uploadTestDocument(SubmitTDocDsRequestModel dsRequestModel, String docId) {
        String filePath = dsRequestModel.getFilePath();
        return uploadFile(filePath, docId);
    }

    /** Download a file from FTP server
     *
     * @param fileName          file name to download
     * @return returns the local file path if file is downloaded successfully, null otherwise
     */
    String downloadFile(String fileName);

    /** Download a file from FTP server
     *
     * @param model             request model containing information on the document to be downloaded
     * @return returns the local file path if file is downloaded successfully, null otherwise
     */
    default String downloadDoc(DownloadDocFileAccessRequestModel model) {
        String documentId = model.getDocumentId();
        return downloadFile(documentId);
    }
    
    /** Check connection status to FTP server
     * 
     * @return true if connection is successful, false otherwise
     */
    boolean checkConnectionStatus();

}
