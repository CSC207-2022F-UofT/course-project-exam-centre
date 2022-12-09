package usecases.course.updatemembers.responsemodels;

import java.util.Map;

public class UpdateCMemTestDocResponseModel {

    private final String testId;
    private final String userId;
    private final String testType;
    private final Integer numOfQuestions;
    private final Float estimatedTime;
    private final String testName;
    private final Map<String, UpdateCMemSolutionDocResponseModel> solutionModels;

    public UpdateCMemTestDocResponseModel(
            String testId,
            String userId,
            String testType,
            Integer numOfQuestions,
            Float estimatedTime,
            String testName,
            Map<String, UpdateCMemSolutionDocResponseModel> solutionModels) {

        this.testId = testId;
        this.userId = userId;
        this.testType = testType;
        this.numOfQuestions = numOfQuestions;
        this.estimatedTime = estimatedTime;
        this.testName = testName;
        this.solutionModels = solutionModels;
    }

    public Map<String, UpdateCMemSolutionDocResponseModel> getSolutionModels() {
        return this.solutionModels;
    }

    public String getTestId() {
        return this.testId;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getTestType() {
        return this.testType;
    }

    public Integer getNumOfQuestions() {
        return this.numOfQuestions;
    }

    public Float getEstimatedTime() {
        return this.estimatedTime;
    }

    public String getTestName() {
        return this.testName;
    }

}
