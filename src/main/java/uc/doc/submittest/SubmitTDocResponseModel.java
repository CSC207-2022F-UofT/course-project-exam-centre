package uc.doc.submittest;

import java.time.LocalDateTime;

public class SubmitTDocResponseModel {

    private final String testDocID;

    private final LocalDateTime timestamp;

    public SubmitTDocResponseModel(String testDocID, LocalDateTime timestamp) {
        this.testDocID = testDocID;
        this.timestamp = timestamp;
    }

    public String getTestDocID() {
        return testDocID;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
