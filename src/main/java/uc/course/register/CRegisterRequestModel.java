package uc.course.register;

public class CRegisterRequestModel {
    public String courseId;
    private String courseName;
    private String courseCode;

    public CRegisterRequestModel(String courseName, String courseCode) {
        this.courseName = courseName;
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseId() {
        return courseId;
    }
}
