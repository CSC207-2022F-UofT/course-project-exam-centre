package uc.doc.submittest;

public class SubTDocRequestModel {

    private final String name;

    private final Integer numberOfQuestions;

    private final Float recordedTime;

    private final String testType;

    private final String userID;

    private final String courseID;

    private final String filePath;

    public SubTDocRequestModel(String name,
                               Integer numberOfQuestions,
                               Float recordedTime,
                               String testType,
                               String userID,
                               String courseID,
                               String filePath) {
        this.name = name;
        this.numberOfQuestions = numberOfQuestions;
        this.recordedTime = recordedTime;
        this.testType = testType;
        this.userID = userID;
        this.courseID = courseID;
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getName() {
        return name;
    }

    public Integer getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public Float getRecordedTime() {
        return recordedTime;
    }

    public String getTestType() {
        return testType;
    }

    public String getUserID() {
        return userID;
    }

    public String getCourseID() {
        return courseID;
    }
}
