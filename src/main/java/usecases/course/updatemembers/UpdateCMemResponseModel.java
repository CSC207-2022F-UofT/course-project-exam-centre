package usecases.course.updatemembers;

import usecases.course.updatemembers.responsemodels.UpdateCMemCourseResponseModel;
import usecases.course.updatemembers.responsemodels.UpdateCMemUserResponseModel;

import java.util.Map;

/** UpdateCMemResponseModel is responsible for packaging data in a way for a presenter to use.
 * @layer use cases
 */
public class UpdateCMemResponseModel {
    private final UpdateCMemUserResponseModel currentUserModel;
    private final Map<String, UpdateCMemCourseResponseModel> usersCourseModels;

    /** Constructs a UpdateCMemResponseModel containing a timestamp, userCourseList and an updateMembershipStatus
    *
    * @param currentUser            response model containing information on the current user
    * @param usersCourseModels      map of course models which correspond to a user
    */
    public UpdateCMemResponseModel(UpdateCMemUserResponseModel currentUser,
                                   Map<String, UpdateCMemCourseResponseModel> usersCourseModels) {

        this.currentUserModel = currentUser;
        this.usersCourseModels = usersCourseModels;
    }

    /** Gets the user response model which contains information on the current user
     *
     * @return user response model of current user
     */
    public UpdateCMemUserResponseModel getCurrentUserModel() {
        return this.currentUserModel;
    }

    /** Gets the course models which correspond to a user
     *
     * @return map of course models which correspond to a user
     */
    public Map<String, UpdateCMemCourseResponseModel> getUsersCourseModels() {
        return this.usersCourseModels;
    }
}
