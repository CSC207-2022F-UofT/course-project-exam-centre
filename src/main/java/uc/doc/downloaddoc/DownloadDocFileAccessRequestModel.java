package uc.doc.downloaddoc;

public class DownloadDocFileAccessRequestModel {
    private final String documentId;
    private final String userId;
    private final String downloadPath;

    public DownloadDocFileAccessRequestModel(String documentId, String userId, String downloadPath) {
        this.documentId = documentId;
        this.userId = userId;
        this.downloadPath = downloadPath;
    }

    public String getDocumentId() {
        return this.documentId;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getDownloadPath() {
        return this.downloadPath;
    }
}
