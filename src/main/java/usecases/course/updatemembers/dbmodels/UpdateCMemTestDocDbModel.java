package usecases.course.updatemembers.dbmodels;

public interface UpdateCMemTestDocDbModel {
    String getTestId();
    String getUserId();
    String getTestType();
    Integer getNumOfQuestions();
    Float getEstimatedTime();
    String getTestName();
}
