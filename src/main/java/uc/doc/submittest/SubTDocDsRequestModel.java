package uc.doc.submittest;

public class SubTDocDsRequestModel {

    private final String name;

    private final Integer numberOfQuestions;

    private final String userID;

    private final String courseID;

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
        this.userID = userID;
        this.courseID = courseID;
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

    public String getUserID() {
        return userID;
    }

    public String getCourseID() {
        return courseID;
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
