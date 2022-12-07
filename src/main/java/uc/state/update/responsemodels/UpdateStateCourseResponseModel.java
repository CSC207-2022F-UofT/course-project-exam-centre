package uc.state.update.responsemodels;

import java.util.HashMap;
import java.util.Map;

public class UpdateStateCourseResponseModel {

    private final String courseId;
    private final String courseCode;
    private final String courseName;
    private final Map<String, UpdateStateTestDocResponseModel> testModels;

    public UpdateStateCourseResponseModel(
            String courseId,
            String courseCode,
            String courseName,
            Map<String, UpdateStateTestDocResponseModel> testModels) {
        this.courseId = courseId;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.testModels = testModels;
    }

    public Map<String, UpdateStateTestDocResponseModel> getTests(){
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
