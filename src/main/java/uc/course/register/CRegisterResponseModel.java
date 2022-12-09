package uc.course.register;

public class CRegisterResponseModel {
    private final String courseId;
    private final String courseCode;
    private final String courseName;

    /**
     * @param courseId the unique identifier of the course
     * @param courseCode the code of the course e.g. CSC207
     * @param courseName the name of the course e.g. Software Design
     */
    public CRegisterResponseModel(String courseId,
                                              String courseCode,
                                              String courseName) {
        this.courseId = courseId;
        this.courseCode = courseCode;
        this.courseName = courseName;
    }

    /**
     * @return the unique identifier of the course
     */
    public String getCourseId() {
        return this.courseId;
    }

    /**
     * @return the code of the course e.g. CSC207
     */
    public String getCourseCode() {
        return this.courseCode;
    }

    /**
     * @return the name of the course e.g. Software Design
     */
    public String getCourseName() {
        return this.courseName;
    }
}
