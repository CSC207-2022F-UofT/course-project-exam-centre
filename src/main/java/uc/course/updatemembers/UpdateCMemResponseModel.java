package uc.course.updatemembers;

import java.util.List;

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
