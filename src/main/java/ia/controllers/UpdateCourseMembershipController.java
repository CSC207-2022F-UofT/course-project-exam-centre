package ia.controllers;

import uc.course.updatemembers.UpdateCMemInputBoundary;
import uc.course.updatemembers.UpdateCMemRequestModel;
import uc.course.updatemembers.UpdateCMemResponseModel;

import java.util.List;

public class UpdateCourseMembershipController {
    private final UpdateCMemInputBoundary inputBoundary;

    public UpdateCourseMembershipController(
            UpdateCMemInputBoundary inputBoundary){
            this.inputBoundary = inputBoundary;
    }

    public UpdateCMemResponseModel updateCourseMembership(
            String userId,
            List<String> newCoursesList) {

        UpdateCMemRequestModel requestModel = new UpdateCMemRequestModel(
                userId,
                newCoursesList);

        return inputBoundary.updateCourseMembership(requestModel);
    }
}
