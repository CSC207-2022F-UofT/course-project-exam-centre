package uc.course.updatemembers;

import uc.course.updatemembers.dbmodels.UpdateCMemUserDbModel;
import uc.course.updatemembers.responsemodels.UpdateCMemCourseResponseModel;
import uc.course.updatemembers.responsemodels.UpdateCMemUserResponseModel;

import java.util.Map;

/** UpdateCMemResponseModel is responsible for packaging data in a way for a presenter to use.
 * @layer use cases
 */
public class UpdateCMemResponseModel {
    private final UpdateCMemUserResponseModel currentUserModel;
    private final Map<String, UpdateCMemCourseResponseModel> usersCourseModels;

    /** Constructs a UpdateCMemResponseModel containing a timestamp, userCourseList and an updateMembershipStatus
     */
    public UpdateCMemResponseModel(UpdateCMemUserResponseModel currentUser,
                                   Map<String, UpdateCMemCourseResponseModel> usersCourseModels) {

        this.currentUserModel = currentUser;
        this.usersCourseModels = usersCourseModels;
    }

    /**
     * @return the current user model
     */
    public UpdateCMemUserResponseModel getCurrentUserModel() {
        return this.currentUserModel;
    }

    /**
     * @return a mapping of course ids to course response models
     */
    public Map<String, UpdateCMemCourseResponseModel> getUsersCourseModels() {
        return this.usersCourseModels;
    }
}
