package uc.doc.voteonsolution;

public class VoteSDocRequestModel {
    private final String solutionId;
    private final String userId;
    private final boolean vote;

    public VoteSDocRequestModel(String solutionId, String userId, boolean vote) {
        this.solutionId = solutionId;
        this.userId = userId;
        this.vote = vote;
    }

    public String getSolutionId() {
        return this.solutionId;
    }

    public String getUserId() {
        return this.userId;
    }

    public boolean getVote() {
        return this.vote;
    }
}
