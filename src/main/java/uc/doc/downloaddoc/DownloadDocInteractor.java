package uc.doc.downloaddoc;

import java.time.LocalDateTime;

import entities.StateTracker;
import entities.User;

/** DownloadDocInteractor implements the ability to download a 
 * test or solution document to a local machine
 * @layer Use cases
 */
public class DownloadDocInteractor implements DownloadDocInputBoundary {
    private final DownloadDocFileAccessGateway downloadDocFileAccessGateway;
    private final DownloadDocOutputBoundary downloadDocOutputBoundary;
    private final StateTracker stateTracker;
    
    /** Creates an interactor for the download doc use case which contains the
     * FileAccessGateway, file output boundary and StateTracker
     * @param downloadDocFileAccessGateway      Provides methods to download a document
     * @param downloadDocOutputBoundary         Provides methods to update view
     * @param stateTracker                      Used for tracking entities in the program
     */
    public DownloadDocInteractor(
        DownloadDocFileAccessGateway downloadDocFileAccessGateway, 
        DownloadDocOutputBoundary downloadDocOutputBoundary, 
        StateTracker stateTracker) 
        {
        this.downloadDocFileAccessGateway = downloadDocFileAccessGateway;
        this.downloadDocOutputBoundary = downloadDocOutputBoundary;
        this.stateTracker = stateTracker;
    }

    /** Downloads a document from the server to a local machine if it is not yet downloaded
     * @param model         The download doc model containing all the relevant information for downloading a document
     * @return If completed, the success view response model, containing information to be presented to the user
     */
    @Override
    public DownloadDocResponseModel downloadDoc(DownloadDocRequestModel model) {
        User user = stateTracker.getCurrentUser();
        String documentId = model.getDocumentId();
        String downloadPath;

        if (!stateTracker.checkIfDocumentDownloaded(documentId)) {
            DownloadDocFileAccessRequestModel downloadDocFileAccessRequestModel = new DownloadDocFileAccessRequestModel(documentId, user.getId());
            downloadPath = downloadDocFileAccessGateway.downloadDoc(downloadDocFileAccessRequestModel);
            stateTracker.updateDownloadedDocuments(documentId, downloadPath);
        } else {
            downloadPath = stateTracker.getDownloadedDocumentPath(documentId);
        }
    
        DownloadDocResponseModel downloadDocResponseModel = new DownloadDocResponseModel(documentId, downloadPath);

        return downloadDocOutputBoundary.prepareSuccessView(downloadDocResponseModel);
    }
    
}
