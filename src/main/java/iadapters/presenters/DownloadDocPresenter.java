package iadapters.presenters;

import iadapters.exceptions.DownloadDocFailed;
import iadapters.gateways.ViewManagerGateway;
import iadapters.viewmodels.MainViewModel;
import usecases.doc.downloaddoc.DownloadDocOutputBoundary;
import usecases.doc.downloaddoc.DownloadDocResponseModel;

import java.util.Map;

/**
 * DownloadDocPresenter updates the views after the DownloadDoc use case
 * @layer interface adapters
 */
public class DownloadDocPresenter implements DownloadDocOutputBoundary{

    private final ViewManagerGateway viewManagerGateway;
    private final MainViewModel viewModel;

    /**
     * Creates a presenter for updating the view
     * @param viewManagerGateway Used for managing and updating views
     */
    public DownloadDocPresenter(
            ViewManagerGateway viewManagerGateway, MainViewModel viewModel) {
        this.viewManagerGateway = viewManagerGateway;
        this.viewModel = viewModel;
    }
    
    /** Prepares SuccessView when a document is successfully downloaded
     *
     * @param responseModel Response model containing the documentId and
     *                      local file path of the downloaded document
     * @return model Response model corresponding to successful
     */
    @Override
    public DownloadDocResponseModel prepareSuccessView(
            DownloadDocResponseModel responseModel) {

        Map<String, String> localDocumentPaths
                = viewModel.getLocalDocumentPaths();

        localDocumentPaths.put(
                responseModel.getDocumentId(),
                responseModel.getDownloadPath()
        );

        viewManagerGateway.updateViews();

        return responseModel;
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
