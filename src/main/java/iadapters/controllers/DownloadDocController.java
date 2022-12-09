package iadapters.controllers;

import usecases.doc.downloaddoc.DownloadDocInputBoundary;
import usecases.doc.downloaddoc.DownloadDocRequestModel;
import usecases.doc.downloaddoc.DownloadDocResponseModel;

/**
 *  DownloadDocController provides an entry way into the DownloadDoc use case
 * @layer interface adapters
 */
public class DownloadDocController {
    private final DownloadDocInputBoundary downloadDocInputBoundary;

    /**
     * Creates an instance of DownloadDocController with an input boundary
     * @param downloadDocInputBoundary provides methods to invoke use case
     */
    public DownloadDocController(DownloadDocInputBoundary downloadDocInputBoundary) {
        this.downloadDocInputBoundary = downloadDocInputBoundary;
    }

    /**
     * Implements method to invoke use case
     * @param documentId id of the document being downloaded
     * @param userId id of the user downloading the document
     * @return a response model corresopnding to the request model
     */
    public DownloadDocResponseModel createInput(String documentId, String userId){
        DownloadDocRequestModel downloadDocRequestModel = new DownloadDocRequestModel(documentId, userId);
        return downloadDocInputBoundary.downloadDoc(downloadDocRequestModel);
    }
}
