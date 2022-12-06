package ia.gateways;

import uc.doc.downloaddoc.DownloadDocFileAccessGateway;
import uc.doc.downloaddoc.DownloadDocFileAccessRequestModel;
import uc.doc.submitsolution.SubSDocDsRequestModel;
import uc.doc.submitsolution.SubSDocFileAccessGateway;
import uc.doc.submittest.SubTDocDsRequestModel;
import uc.doc.submittest.SubTDocFileAccessGateway;

public interface FileAccessGateway extends SubSDocFileAccessGateway, SubTDocFileAccessGateway, DownloadDocFileAccessGateway{
    
    boolean uploadFile(String filePath, String docId);

    default boolean uploadSolutionDocument(SubSDocDsRequestModel model, String docId) {
        String filePath = model.getFilePath();
        return uploadFile(filePath, docId);
    }

    default boolean uploadTestDocument(SubTDocDsRequestModel dsRequestModel, String docId) {
        String filePath = dsRequestModel.getFilePath();
        return uploadFile(filePath, docId);
    }

    boolean downloadFile(String fileName, String downloadPath);

    default boolean downloadDoc(DownloadDocFileAccessRequestModel model) {
        String documentId = model.getDocumentId();
        String downloadPath = model.getDownloadPath();
        return downloadFile(documentId, downloadPath);
    }

}
