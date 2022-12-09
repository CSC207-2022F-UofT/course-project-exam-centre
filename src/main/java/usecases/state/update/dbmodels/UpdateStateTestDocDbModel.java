package usecases.state.update.dbmodels;

public interface UpdateStateTestDocDbModel {
    String getTestId();
    String getUserId();
    String getTestType();
    Integer getNumOfQuestions();
    Float getEstimatedTime();
    String getTestName();
}
