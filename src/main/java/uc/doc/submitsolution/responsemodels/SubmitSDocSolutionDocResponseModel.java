package uc.doc.submitsolution.responsemodels;

public class SubmitSDocSolutionDocResponseModel {

    private final String solutionId;
    private final Integer voteTotal;
    private final Float recordedScore;
    private final Float estimatedTime;
    private final String solutionName;
    private final SubmitSDocMessageTreeResponseModel rootMessage;

    public SubmitSDocSolutionDocResponseModel(
            String solutionId,
            Integer voteTotal,
            Float recordedScore,
            Float estimatedTime,
            String solutionName,
            SubmitSDocMessageTreeResponseModel rootMessage) {

        this.solutionId = solutionId;
        this.voteTotal = voteTotal;
        this.recordedScore = recordedScore;
        this.estimatedTime = estimatedTime;
        this.solutionName = solutionName;
        this.rootMessage = rootMessage;
    }

    public String getSolutionId() {
        return this.solutionId;
    }

    public Integer getVoteTotal() {
        return this.voteTotal;
    }

    public Float getRecordedScore() {
        return this.recordedScore;
    }

    public Float getEstimatedTime() {
        return this.estimatedTime;
    }

    public SubmitSDocMessageTreeResponseModel getRootMessage() {
        return this.rootMessage;
    }

    public String getSolutionName() {
        return this.solutionName;
    }

}
