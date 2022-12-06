package uc.doc.downloaddoc;

public interface DownloadDocOutputBoundary {

    DownloadDocResponseModel prepareSuccessView(DownloadDocResponseModel model);

    DownloadDocRequestModel prepareFailureView(String errorMessage);
    
}
