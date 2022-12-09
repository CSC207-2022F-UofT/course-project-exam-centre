package iadapters.viewmodels;

public class CourseInfoSubViewModel {

    private final String courseId;
    private final String courseCode;
    private final String courseName;

    public CourseInfoSubViewModel(String courseId,
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
