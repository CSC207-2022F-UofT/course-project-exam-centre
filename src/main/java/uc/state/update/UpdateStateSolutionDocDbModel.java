package uc.state.update;

public interface UpdateStateSolutionDocDbModel {
    String getSolutionId();
    String getSolutionName();
    String getTestId();
    String getUserId();
    Integer getVoteTotal();
    Float getRecordedScore();
    Float getEstimatedTime();
    String getRootMessageId();
}
