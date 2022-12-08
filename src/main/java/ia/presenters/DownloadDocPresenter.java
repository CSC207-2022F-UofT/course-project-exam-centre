package ia.presenters;

import ia.exceptions.DownloadDocFailed;
import ia.gateways.ViewManagerGateway;
import uc.doc.downloaddoc.DownloadDocOutputBoundary;
import uc.doc.downloaddoc.DownloadDocResponseModel;

public class DownloadDocPresenter implements DownloadDocOutputBoundary{

    private final ViewManagerGateway viewManagerGateway;

    /**
     * Creates a presenter for updating the view
     * @param viewManagerGateway Used for managing and updating views
     */
    public DownloadDocPresenter(ViewManagerGateway viewManagerGateway) {
        this.viewManagerGateway = viewManagerGateway;
    }
    
    /** Prepares SuccessView when a document is successfully downloaded
     *
     * @param model Response model containing the documentId and local file path of the downloaded document
     * @return model Response model corresponding to successful
     */
    @Override
    public DownloadDocResponseModel prepareSuccessView(DownloadDocResponseModel model) {
        return model;
    }

    /** Prepares the failView when the download is unsuccessful.
     *
     * @param errorMessage A string containing the error message
     * @throws DownloadDocFailed when the download doc use case fails.
     */
    @Override
    public DownloadDocResponseModel prepareFailureView(String errorMessage) {
        // TODO: prepare failure view
        viewManagerGateway.showError(errorMessage, "Document Download Failed");
        throw new DownloadDocFailed(errorMessage);
    }
    
}
