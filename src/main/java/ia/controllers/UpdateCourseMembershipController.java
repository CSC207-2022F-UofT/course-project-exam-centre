package ia.controllers;

import uc.course.updatemembers.UpdateCMemOutputBoundary;
import uc.course.updatemembers.UpdateCMemRequestModel;
import uc.course.updatemembers.UpdateCMemResponseModel;

import java.util.List;

public class UpdateCourseMembershipController {

    /**
     * The input boundary for the course register use case
     */
    private final UpdateCMemOutputBoundary inputBoundary;

    /**
     * Creates a new UpdateCourseMembershipController object for taking inputs from the related view.
     * @param inputBoundary The input boundary from the course register use case
     */
    public UpdateCourseMembershipController(UpdateCMemOutputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    /**
     * Called upon the user submitting their course selection, will add the user to the specified course
     * @param userID The ID of the user updating their course membership
     * @param newCourseList A list of new courses to add
     * @return An instance of CRegisterResponseModel
     */
    public UpdateCMemResponseModel registerCourse(String userID, List<String> newCourseList) {
        UpdateCMemRequestModel requestModel = new UpdateCMemRequestModel(userID, newCourseList);
            return inputBoundary.updateCourseMembership(requestModel);
    }
}
