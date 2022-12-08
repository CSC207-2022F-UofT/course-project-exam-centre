package ia.gateways.models;

import uc.course.updatemembers.dbmodels.UpdateCMemTestDocDbModel;
import uc.state.update.dbmodels.UpdateStateTestDocDbModel;

public class TestDocDbResponseModel
        implements UpdateStateTestDocDbModel,
        UpdateCMemTestDocDbModel {

    private final String testId;
    private final String userId;
    private final String testType;
    private final Integer numOfQuestions;
    private final Float estimatedTime;
    private final String testName;

    public TestDocDbResponseModel(String testId,
                                  String userId,
                                  String testType,
                                  Integer numOfQuestions,
                                  Float estimatedTime,
                                  String testName) {
        this.testId = testId;
        this.userId = userId;
        this.testType = testType;
        this.numOfQuestions = numOfQuestions;
        this.estimatedTime = estimatedTime;
        this.testName = testName;
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
