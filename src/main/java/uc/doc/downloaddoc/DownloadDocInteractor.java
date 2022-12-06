package uc.doc.downloaddoc;

import java.time.LocalDateTime;

import entities.StateTracker;
import entities.User;

public class DownloadDocInteractor implements DownloadDocInputBoundary {
    private final DownloadDocFileAccessGateway downloadDocFileAccessGateway;
    private final DownloadDocOutputBoundary downloadDocOutputBoundary;
    private final StateTracker stateTracker;
 
    public DownloadDocInteractor(
        DownloadDocFileAccessGateway downloadDocFileAccessGateway, 
        DownloadDocOutputBoundary downloadDocOutputBoundary, 
        StateTracker stateTracker) 
        {
        this.downloadDocFileAccessGateway = downloadDocFileAccessGateway;
        this.downloadDocOutputBoundary = downloadDocOutputBoundary;
        this.stateTracker = stateTracker;
    }

    @Override
    public DownloadDocResponseModel downloadDoc(DownloadDocRequestModel model) {
        User user = stateTracker.getCurrentUser();
        String documentId = model.getDocumentId();
        String downloadPath = model.getDownloadPath();

        if (!checkIfDocumentDownloaded(documentId)) {
            DownloadDocFileAccessRequestModel downloadDocFileAccessRequestModel = new DownloadDocFileAccessRequestModel(documentId, user.getId(), downloadPath);
            if (downloadDocFileAccessGateway.downloadDoc(downloadDocFileAccessRequestModel)) {
                updateDownloadedDocuments(documentId, downloadPath);
            }
        } else {
            downloadPath = getDownloadedDocumentPath(documentId);
        }
    
        DownloadDocResponseModel downloadDocResponseModel = new DownloadDocResponseModel(documentId, downloadPath, LocalDateTime.now());

        return downloadDocOutputBoundary.prepareSuccessView(downloadDocResponseModel);
    }
    
}
