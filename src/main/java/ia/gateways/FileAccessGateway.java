package ia.gateways;

import uc.doc.submitsolution.SubSDocDsRequestModel;
import uc.doc.submitsolution.SubSDocFileAccessGateway;
import uc.doc.submittest.SubTDocDsRequestModel;
import uc.doc.submittest.SubTDocFileAccessGateway;

public interface FileAccessGateway extends SubSDocFileAccessGateway, SubTDocFileAccessGateway{
    
    boolean uploadFile(String filePath, String docId);

    default boolean uploadSolutionDocument(SubSDocDsRequestModel model, String docId) {
        String filePath = model.getFilePath();
        return uploadFile(filePath, docId);
    }

    default boolean uploadTestDocument(SubTDocDsRequestModel dsRequestModel, String docId) {
        String filePath = dsRequestModel.getFilePath();
        return uploadFile(filePath, docId);
    }

}
