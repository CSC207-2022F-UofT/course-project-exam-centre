package uc.doc.submitsolution;

import entities.SolutionDocument;

import java.io.File;
import java.time.LocalDateTime;

public class SubSDocResponseModel {

    private final String solutionDocID;

    private final String testDocID;

    private final LocalDateTime timestamp;

    public SubSDocResponseModel(String solutionDocID, String testDocID, LocalDateTime timestamp) {
        this.solutionDocID = solutionDocID;
        this.timestamp = timestamp;
        this.testDocID = testDocID;
    }

    public String getSolutionDoc() {
        return solutionDocID;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getTestDocID() {
        return testDocID;
    }
}
