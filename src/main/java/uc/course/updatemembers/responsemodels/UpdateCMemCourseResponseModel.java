package uc.course.updatemembers.responsemodels;

import java.util.Map;

public class UpdateCMemCourseResponseModel {

    private final String courseId;
    private final String courseCode;
    private final String courseName;
    private final Map<String, UpdateCMemTestDocResponseModel> testModels;

    public UpdateCMemCourseResponseModel(
            String courseId,
            String courseCode,
            String courseName,
            Map<String, UpdateCMemTestDocResponseModel> testModels) {
        this.courseId = courseId;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.testModels = testModels;
    }

    public Map<String, UpdateCMemTestDocResponseModel> getTests(){
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
