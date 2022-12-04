package uc.doc.voteonsolution;

import java.time.LocalDateTime;

import entities.SolutionDocument;

public class VoteSDocResponseModel {
    private final int voteTotal;
    private final SolutionDocument solutionDoc;
    private final LocalDateTime timestamp;

    public VoteSDocResponseModel(int voteTotal, SolutionDocument solutionDoc, LocalDateTime timestamp) {
        this.voteTotal = voteTotal;
        this.solutionDoc = solutionDoc;
        this.timestamp = timestamp;
    }

    public int getVoteTotal() {
        return this.voteTotal;
    }

    public SolutionDocument getSolutionDoc() {
        return this.solutionDoc;
    }

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }
}
