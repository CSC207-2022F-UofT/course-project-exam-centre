package uc.doc.submittest;

public class SubTDocDsRequestModel {

    private final String name;

    private final Integer numberOfQuestions;

    private final String userId;

    private final String courseId;

    private final String filePath;

    private final Float recordedTime;

    private final String testType;

    public SubTDocDsRequestModel(String name,
                                 Integer numberOfQuestions,
                                 Float estimatedTime,
                                 String testType,
                                 String userID,
                                 String courseID,
                                 String filePath) {
        this.name = name;
        this.numberOfQuestions = numberOfQuestions;
        this.userId = userID;
        this.courseId = courseID;
        this.filePath = filePath;
        this.recordedTime = estimatedTime;
        this.testType = testType;
    }

    public String getName() {
        return name;
    }

    public Integer getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public String getUserId() {
        return userId;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getFilePath() {
        return filePath;
    }

    public Float getRecordedTime() {
        return recordedTime;
    }

    public String getTestType() {
        return testType;
    }
}
