package uc.course.updatemembers;

import java.util.List;

/** UpdateCMemResponseModel is responsible for packaging data in a way for a presenter to use.
 * @layer use cases
 */
public class UpdateCMemResponseModel {
    private String timestamp;
    private List<String> userCourseList;
    private boolean updateMembershipStatus;

    /** Constructs a UpdateCMemResponseModel containing a timestamp, userCourseList and an updateMembershipStatus
     *
     * @param timestamp how long it takes the membership to update
     * @param userCourseList the course list of the user
     * @param updateMembershipStatus if the membership was updated or not
     */
    public UpdateCMemResponseModel(String timestamp, List<String> userCourseList, boolean updateMembershipStatus) {
        this.timestamp = timestamp;
        this.userCourseList = userCourseList;
        this.updateMembershipStatus = updateMembershipStatus;
    }
}
