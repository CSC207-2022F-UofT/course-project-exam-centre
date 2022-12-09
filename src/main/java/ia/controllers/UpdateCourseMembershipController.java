package ia.controllers;

import uc.course.updatemembers.UpdateCMemInputBoundary;
import uc.course.updatemembers.UpdateCMemRequestModel;
import uc.course.updatemembers.UpdateCMemResponseModel;

import java.util.List;

/**
 * UpdateCourseMembershipController provides an entry way into the UpdateCourseMembership use case
 * @layer interface adapters
 */
public class UpdateCourseMembershipController {

    /**
     * The input boundary for the course register use case
     */
    private final UpdateCMemInputBoundary inputBoundary;

    /**
     * Creates a new UpdateCourseMembershipController object for taking inputs from the related view.
     * @param inputBoundary The input boundary from the course register use case
     */
    public UpdateCourseMembershipController(UpdateCMemInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    /**
     * Called upon the user submitting their course selection, will add the user to the specified course
     * @param newCourseList A list of new courses to add
     * @return An instance of CRegisterResponseModel
     */
    public UpdateCMemResponseModel updateCourseMembership(List<String> newCourseList) {
        UpdateCMemRequestModel requestModel = new UpdateCMemRequestModel(newCourseList);
            return inputBoundary.updateCourseMembership(requestModel);
    }
}
