package uc.doc.voteonsolution;

public class VoteSDocDsRequestModel {
    private final String solutionId;
    private final String userId;
    private final int vote;

    public VoteSDocDsRequestModel(String solutionId, String userId, int vote) {
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

    public int getVote() {
        return this.vote;
    }
}
