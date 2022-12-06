package ia.presenters;

import uc.doc.downloaddoc.DownloadDocOutputBoundary;
import uc.doc.downloaddoc.DownloadDocRequestModel;
import uc.doc.downloaddoc.DownloadDocResponseModel;

public class DownloadDocPresenter implements DownloadDocOutputBoundary{

    @Override
    public DownloadDocResponseModel prepareSuccessView(DownloadDocResponseModel model) {
        return model;
    }

    @Override
    public DownloadDocRequestModel prepareFailureView(String errorMessage) {
        // TODO: Update view model here
        return null;
    }
    
}
