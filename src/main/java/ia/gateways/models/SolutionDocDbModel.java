package ia.gateways.models;

import uc.state.update.UpdateStateSolutionDocDbModel;

public class SolutionDocDbModel implements UpdateStateSolutionDocDbModel {

    private final String solutionId;
    private final String testId;
    private final String userId;
    private final Integer voteTotal;
    private final Float recordedScore;
    private final Float estimatedTime;
    private final String rootMessageId;
    private final String solutionName;

    public SolutionDocDbModel(String solutionId,
                              String testId,
                              String userId,
                              Integer voteTotal,
                              Float recordedScore,
                              Float estimatedTime,
                              String rootMessageId,
                              String solutionName) {
        this.solutionId = solutionId;
        this.testId = testId;
        this.userId = userId;
        this.voteTotal = voteTotal;
        this.recordedScore = recordedScore;
        this.estimatedTime = estimatedTime;
        this.rootMessageId = rootMessageId;
        this.solutionName = solutionName;
    }

    public String getTestId() {
        return this.testId;
    }

    public String getUserId() {
        return this.userId;
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

    public String getRootMessageId() {
        return this.rootMessageId;
    }

    public String getSolutionName() {
        return this.solutionName;
    }

}
