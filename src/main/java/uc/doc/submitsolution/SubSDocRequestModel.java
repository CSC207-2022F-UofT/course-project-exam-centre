package uc.doc.submitsolution;

import entities.Course;
import entities.User;

public class SubSDocRequestModel {

    private String name;

    private String id;

    private String filePath;

    private Integer recordedScore;

    private User user;

    private Course course;

    private String rootID;

    private Float recordedTime;

    public SubSDocRequestModel(String name, String id, String filePath, Integer recordedScore, User user, Course course, String rootID, Float recordedTime) {
        this.name = name;
        this.id = id;
        this.filePath = filePath;
        this.recordedScore = recordedScore;
        this.user = user;
        this.course = course;
        this.rootID = rootID;
        this.recordedTime = recordedTime;
    }

    public String getId() {
        return id;
    }
    public String getFilePath() {
        return filePath;
    }

    public Integer getRecordedScore() {
        return recordedScore;
    }

    public Course getCourse() {
        return course;
    }

    public User getUser() {
        return user;
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
}
