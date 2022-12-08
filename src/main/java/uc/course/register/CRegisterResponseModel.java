package uc.course.register;

public class CRegisterResponseModel {
    private final String courseId;
    private final String courseCode;
    private final String courseName;

    public CRegisterResponseModel(String courseId,
                                              String courseCode,
                                              String courseName) {
        this.courseId = courseId;
        this.courseCode = courseCode;
        this.courseName = courseName;
    }

    public String getCourseId() {
        return this.courseId;
    }

    public String getCourseCode() {
        return this.courseCode;
    }

    public String getCourseName() {
        return this.courseName;
    }
}
