package uc.doc.submitsolution;

public class SubSDocRequestModel {

    private final String name;
    private final String filePath;

    private final Float recordedScore;

    private final String userID;

    private final String courseID;

    private final String rootID;

    private final Float recordedTime;

    private final String parentTestID;


    public SubSDocRequestModel(String name, String filePath, Float recordedScore, String userID, String courseID,
                               String rootID, Float recordedTime, String parentTest) {
        this.name = name;
        this.filePath = filePath;
        this.recordedScore = recordedScore;
        this.userID = userID;
        this.courseID = courseID;
        this.rootID = rootID;
        this.recordedTime = recordedTime;
        this.parentTestID = parentTest;
    }

    public String getFilePath() {
        return filePath;
    }

    public Float getRecordedScore() {
        return recordedScore;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getUserID() {
        return userID;
    }

    public String getRootID() {
        return rootID;
    }

    public Float getRecordedTime() {
        return recordedTime;
    }

    public String getName() {
        return name;
    }

    public String getParentTestID() {
        return parentTestID;
    }
}
