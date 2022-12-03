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

    public CRegisterDsRequestModel(String courseName, String courseCode) {
        this.courseName = courseName;
        this.courseCode = courseCode;
    }
    public String getCourseName() {
        return courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }
}
