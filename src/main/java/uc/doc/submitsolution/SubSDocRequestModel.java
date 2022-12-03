package uc.doc.submitsolution;

public class SubSDocRequestModel {

    private final String name;

    private final String filePath;

    private final Float recordedScore;

    private final String courseID;

    private final Float recordedTime;

    private final String parentTestID;


    public SubSDocRequestModel(String name,
                               String filePath,
                               Float recordedScore,
                               String courseID,
                               Float recordedTime,
                               String parentTest) {
        this.name = name;
        this.filePath = filePath;
        this.recordedScore = recordedScore;
        this.courseID = courseID;
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
