package ia.controllers;

import uc.course.register.CRegisterRequestModel;
import uc.course.register.CRegisterResponseModel;
import uc.course.register.CRegisterlnputBoundary;

public class UpdateCourseMembershipController {

    /**
     * The input boundary for the course register use case
     */
    private final CRegisterlnputBoundary inputBoundary;

    /**
     * Creates a new UpdateCourseMembershipController object for taking inputs from the related view.
     * @param inputBoundary The input boundary from the course register use case
     */
    public UpdateCourseMembershipController(CRegisterlnputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    /**
     * Called upon the user submitting their course selection, will add the user to the specified course
     * @param courseName The name of the course the user wishes to register in
     * @param courseID The ID of the course the user wishes to reigster in
     * @return An instance of CRegisterResponseModel
     */
    public CRegisterResponseModel registerCourse(String courseName, String courseID) {
            CRegisterRequestModel requestModel = new CRegisterRequestModel(courseName, courseID);
            return inputBoundary.registerCourse(requestModel);
    }
}
