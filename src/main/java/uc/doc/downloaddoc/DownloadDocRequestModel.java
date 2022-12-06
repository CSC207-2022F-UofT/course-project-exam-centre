package uc.doc.downloaddoc;

public class DownloadDocRequestModel {
    private final String documentId;
    private final String userId;

    public DownloadDocRequestModel(String documentId, String userId) {
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
