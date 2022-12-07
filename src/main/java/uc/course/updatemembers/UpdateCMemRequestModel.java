package uc.course.updatemembers;

import java.util.List;

/** UpdateCMemRequestModel is responsible for packaging data in a way that the UpdateCourseMembershipInteractor
 * can use.
 * @layer use cases
 */
public class UpdateCMemRequestModel {
    private List<String> newCoursesList;

    /** Construct an UpdateCMemRequestModel containing a newCoursesList
     *
     * @param newCoursesList the new course list of the user
     */
    public UpdateCMemRequestModel(List<String> newCoursesList) {
        this.newCoursesList = newCoursesList;
    }

    /** Gets an updated list of the courses a user is enrolled in
     *
     * @return a list of Strings corresponding to the courses the user is enrolled in
     */
    public List<String> getNewCoursesList() {
        return newCoursesList;
    }
}
