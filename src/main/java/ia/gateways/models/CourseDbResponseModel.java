package ia.gateways.models;

import uc.course.updatemembers.dbmodels.UpdateCMemCourseDbModel;
import uc.state.update.dbmodels.UpdateStateCourseDbModel;

public class CourseDbResponseModel
        implements UpdateStateCourseDbModel,
        UpdateCMemCourseDbModel {

    private final String courseId;
    private final String courseCode;
    private final String courseName;

    public CourseDbResponseModel(String courseId,
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
