package usecases.course.register;

/** CRegisterResponseModel is a bundle of data that can be used by a presenter
 * @layer use cases
 */
public class CRegisterResponseModel {
    private final String courseId;
    private final String courseCode;
    private final String courseName;

    /** Create an instance of CRegisterResponseModel that contains information regarding the course
     * to be registered
     * 
     * @param courseId          Id of the course
     * @param courseCode        Code of the course
     * @param courseName        Name of the course
     */
    public CRegisterResponseModel(String courseId,
                                              String courseCode,
                                              String courseName) {
        this.courseId = courseId;
        this.courseCode = courseCode;
        this.courseName = courseName;
    }

    /** Gets the Id of the course 
     *
     * @return the Id of the course
     */
    public String getCourseId() {
        return this.courseId;
    }

    /** Gets the code of the course 
     *
     * @return the code of the course
     */
    public String getCourseCode() {
        return this.courseCode;
    }

    /** Gets the name of the course
     *
     * @return the name of the course
     */
    public String getCourseName() {
        return this.courseName;
    }
}
