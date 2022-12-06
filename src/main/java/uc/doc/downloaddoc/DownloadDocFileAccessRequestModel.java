package uc.doc.downloaddoc;

public class DownloadDocFileAccessRequestModel {
    private final String documentId;
    private final String userId;

    public DownloadDocFileAccessRequestModel(String documentId, String userId) {
        this.documentId = documentId;
        this.userId = userId;
    }

    public String getDocumentId() {
        return this.documentId;
    }

    public String getUserId() {
        return this.userId;
    }

}
