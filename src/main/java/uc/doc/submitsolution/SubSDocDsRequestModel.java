package uc.doc.submitsolution;

public class SubSDocDsRequestModel {

    private final String name;

    private final Float recordedScore;

    private final String userID;

    private final String courseID;

    private final String parentTestID;

    private final String filePath;

    public SubSDocDsRequestModel(String name, String userID, Float recordedScore, String courseID, String parentTestID, String filePath) {
        this.name = name;
        this.recordedScore = recordedScore;
        this.userID = userID;
        this.courseID = courseID;
        this.parentTestID = parentTestID;
        this.filePath = filePath;
    }

    public Float getRecordedScore() {
        return recordedScore;
    }

    public String getUserID() {
        return userID;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getParentTestID() {
        return parentTestID;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getName() {
        return name;
    }
}
