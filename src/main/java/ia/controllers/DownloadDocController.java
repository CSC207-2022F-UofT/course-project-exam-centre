package ia.controllers;

import uc.doc.downloaddoc.DownloadDocInputBoundary;
import uc.doc.downloaddoc.DownloadDocRequestModel;
import uc.doc.downloaddoc.DownloadDocResponseModel;

public class DownloadDocController {
    private final DownloadDocInputBoundary downloadDocInputBoundary;

    public DownloadDocController(DownloadDocInputBoundary downloadDocInputBoundary) {
        this.downloadDocInputBoundary = downloadDocInputBoundary;
    }

    public DownloadDocResponseModel createInput(String documentId, String userId, String downloadPath){
        DownloadDocRequestModel downloadDocRequestModel = new DownloadDocRequestModel(documentId, userId, downloadPath);
        return downloadDocInputBoundary.downloadDoc(downloadDocRequestModel);
    }
}
