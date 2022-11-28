package uc.doc.submitsolution;

import entities.SolutionDocument;

import java.io.File;
import java.time.LocalDateTime;

public class SubSDocResponseModel {

    private SolutionDocument solutionDoc;

    private LocalDateTime timestamp;

    public SubSDocResponseModel(SolutionDocument solutionDoc, LocalDateTime timestamp) {
        this.solutionDoc = solutionDoc;
        this.timestamp = timestamp;
    }

    public SolutionDocument getSolutionDoc() {
        return solutionDoc;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
