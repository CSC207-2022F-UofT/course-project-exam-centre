package iadapters.viewmodels;

import java.util.Map;

public class CourseSubViewModel {

    private final String courseId;
    private final String courseCode;
    private final String courseName;
    private final Map<String, TestDocSubViewModel> testModels;

    public CourseSubViewModel(
            String courseId,
            String courseCode,
            String courseName,
            Map<String, TestDocSubViewModel> testModels) {
        this.courseId = courseId;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.testModels = testModels;
    }

    public Map<String, TestDocSubViewModel> getTests(){
        return this.testModels;
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
