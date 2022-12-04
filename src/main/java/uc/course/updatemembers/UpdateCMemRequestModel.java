package uc.course.updatemembers;

import java.util.List;

/** UpdateCMemRequestModel is responsible for packaging data in a way that the UpdateCourseMembershipInteractor
 * can use.
 * @layer use cases
 */
public class UpdateCMemRequestModel {
    private String userId;
    private List<String> newCoursesList;

    /** Construct an UpdateCMemRequestModel containing a userId and newCoursesList
     *
     * @param userId the userId of the user updating their courses
     * @param newCoursesList the new course list of the user
     */
    public UpdateCMemRequestModel(String userId, List<String> newCoursesList) {
        this.userId = userId;
        this.newCoursesList = newCoursesList;
    }

    /** Gets the userId of the user trying to update their courses
     *
     * @return returns a userId of a user
     */
    public String getUserId() {
        return userId;
    }

    /** Gets an updated list of the courses a user is enrolled in
     *
     * @return a list of Strings corresponding to the courses the user is enrolled in
     */
    public List<String> getNewCoursesList() {
        return newCoursesList;
    }
}
