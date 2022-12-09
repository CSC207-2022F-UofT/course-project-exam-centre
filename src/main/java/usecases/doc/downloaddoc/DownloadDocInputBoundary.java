package usecases.doc.downloaddoc;

/** DownloadDocInputBoundary provides an entry point from the Interface Adapter layer to the Use Case layer for the
 * download doc use case
 * @layer use cases
 */
public interface DownloadDocInputBoundary {
    
    /**
     * Invokes the download document use case
     * @param model a request model containing the documentId and userId
     */
    DownloadDocResponseModel downloadDoc(DownloadDocRequestModel model);

}
