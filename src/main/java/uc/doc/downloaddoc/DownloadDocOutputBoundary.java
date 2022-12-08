package uc.doc.downloaddoc;

/** Provides methods for the DownloadDocInteractor to output data.
 * @layer use cases
 */
public interface DownloadDocOutputBoundary {

    /** Prepares a success view
     * @param model Response model containing the documentId, downloadPath and timestamp of the request
     */
    DownloadDocResponseModel prepareSuccessView(DownloadDocResponseModel model);

    /** Prepares a failure view
     * @param errorMessage The errorMessage that occurs when a failure view happens
     */
    DownloadDocRequestModel prepareFailureView(String errorMessage);
    
}
