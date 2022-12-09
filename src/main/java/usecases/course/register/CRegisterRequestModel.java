package usecases.course.register;

/** CRegisterRequestModel is responsible for formatting data for the CourseRegisterInteractor
 * @layer use cases
 */
public class CRegisterRequestModel {
    private String courseName;
    private String courseCode;

    /** Constructs an instance of a CRegisterRequestModel with a courseName and courseCode
     *
     * @param courseName name of the course being created
     * @param courseCode code of the course being created
     */
    public CRegisterRequestModel(String courseName, String courseCode) {
        this.courseName = courseName;
        this.courseCode = courseCode;
    }

    /** Gets the course name of the course
     *
     * @return a string containing the course name
     */
    public String getCourseName() {
        return courseName;
    }
    /** Gets the course code of the course
     *
     * @return a string containing the course code
     */
    public String getCourseCode() {
        return courseCode;
    }
}