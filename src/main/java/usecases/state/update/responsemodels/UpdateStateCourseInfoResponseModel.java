package usecases.state.update.responsemodels;

public class UpdateStateCourseInfoResponseModel {

    private final String courseId;
    private final String courseCode;
    private final String courseName;

    public UpdateStateCourseInfoResponseModel(String courseId,
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
