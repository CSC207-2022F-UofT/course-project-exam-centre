package usecases.course.updatemembers.dbmodels;

public interface UpdateCMemSolutionDocDbModel {
    String getSolutionId();
    String getSolutionName();
    String getTestId();
    String getUserId();
    Integer getVoteTotal();
    Float getRecordedScore();
    Float getEstimatedTime();
    String getRootMessageId();
}
