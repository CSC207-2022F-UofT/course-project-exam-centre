package uc.course.updatemembers;

import java.util.List;

/** UpdateCMemResponseModel is responsible for packaging data in a way for a presenter to use.
 * @layer use cases
 */
public class UpdateCMemResponseModel {
    private String timestamp;
    private List<String> userCourseList;
    private boolean updateMembershipStatus;

    public UpdateCMemResponseModel(String timestamp, List<String> userCourseList, boolean updateMembershipStatus) {
        this.timestamp = timestamp;
        this.userCourseList = userCourseList;
        this.updateMembershipStatus = updateMembershipStatus;
    }
}
