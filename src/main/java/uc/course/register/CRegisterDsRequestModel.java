package uc.course.register;

import java.util.List;

/** CRegisterDsRequestModel is responsible for bundling information in a way that
 * can be then stored in persistent data
 * @layer use cases
 */
public class CRegisterDsRequestModel {
    private String courseName;
    private String courseCode;
    private List<String> testIds;
    private List<String> members;

    /** Constructs an instance of the CRegisterDsRequestModel with the courses name and course code
     *
     * @param courseName the name of the course being created
     * @param courseCode the code of the course being created
     */
    public CRegisterDsRequestModel(String courseName, String courseCode) {
        this.courseName = courseName;
        this.courseCode = courseCode;
    }

    /** Gets the name of the course being created
     *
     * @return a String of the name of the course
     */
    public String getCourseName() {
        return courseName;
    }

    /** Gets the code of the course being created
     *
     * @return a String of the code of the course
     */
    public String getCourseCode() {
        return courseCode;
    }
}
