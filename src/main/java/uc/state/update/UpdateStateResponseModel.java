package uc.state.update;

import uc.state.update.responsemodels.UpdateStateCourseInfoResponseModel;
import uc.state.update.responsemodels.UpdateStateCourseResponseModel;
import uc.state.update.responsemodels.UpdateStateUserResponseModel;

import java.util.Map;

public class UpdateStateResponseModel {
    private final UpdateStateUserResponseModel currentUserModel;
    private final Map<String, UpdateStateCourseResponseModel> usersCourseModels;
    private final Map<String, UpdateStateCourseInfoResponseModel> courseInfoModels;

    UpdateStateResponseModel(UpdateStateUserResponseModel currentUser,
                             Map<String, UpdateStateCourseResponseModel> usersCourseModels,
                             Map<String, UpdateStateCourseInfoResponseModel> courseInfoModels) {

        this.currentUserModel = currentUser;
        this.usersCourseModels = usersCourseModels;
        this.courseInfoModels = courseInfoModels;
    }

    public UpdateStateUserResponseModel getCurrentUserModel() {
        return this.currentUserModel;
    }

    public Map<String, UpdateStateCourseResponseModel> getCurrentUserCourseModels() {
        return this.usersCourseModels;
    }

    public Map<String, UpdateStateCourseInfoResponseModel> getCourseInfoModels() {
        return this.courseInfoModels;
    }
}
