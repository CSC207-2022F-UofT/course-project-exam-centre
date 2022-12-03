package uc.course.register;

public class CRegisterRequestModel {
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
}
