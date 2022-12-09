package uc.course.updatemembers.responsemodels;

import java.util.Map;

public class UpdateCMemCourseResponseModel {

    private final String courseId;
    private final String courseCode;
    private final String courseName;
    private final Map<String, UpdateCMemTestDocResponseModel> testModels;

    /**
     * @param courseId the unique identifier of the course
     * @param courseCode the code of the course e.g. CSC207
     * @param courseName the name of the course e.g. Software Design
     * @param testModels the models including the tests
     */
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

    /**
     * @return the models including the tests
     */
    public Map<String, UpdateCMemTestDocResponseModel> getTests(){
        return this.testModels;
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
