package uc.doc.downloaddoc;

import java.time.LocalDateTime;

public class DownloadDocResponseModel {
    private final String documentId;
    private final String downloadPath;
    private final LocalDateTime timestamp;

    public DownloadDocResponseModel(String documentId, String downloadPath, LocalDateTime timestamp) {
        this.documentId = documentId;
        this.downloadPath = downloadPath;
        this.timestamp = timestamp;
    }

    public String getDocumentId() {
        return this.documentId;
    }

    public String getDownloadPath() {
        return this.downloadPath;
    }

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }
}
