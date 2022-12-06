package ia.gateways;

import uc.doc.submitsolution.SubmitSDocDsRequestModel;
import uc.doc.submitsolution.SubmitSDocFileAccessGateway;
import uc.doc.submittest.SubTDocDsRequestModel;
import uc.doc.submittest.SubmitTDocFileAccessGateway;

public interface FileAccessGateway extends SubmitSDocFileAccessGateway, SubmitTDocFileAccessGateway {
    
    boolean uploadFile(String filePath, String docId);

    default boolean uploadSolutionDocument(SubmitSDocDsRequestModel model, String docId) {
        String filePath = model.getFilePath();
        return uploadFile(filePath, docId);
    }

    default boolean uploadTestDocument(SubTDocDsRequestModel dsRequestModel, String docId) {
        String filePath = dsRequestModel.getFilePath();
        return uploadFile(filePath, docId);
    }

}
