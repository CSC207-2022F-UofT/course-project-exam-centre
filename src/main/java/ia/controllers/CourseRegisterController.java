package ia.controllers;

import uc.course.register.CRegisterInputBoundary;
import uc.course.register.CRegisterRequestModel;
import uc.course.register.CRegisterResponseModel;

/**
 * CourseRegisterController provides an entry way into the CourseRegister use case
 * @layer interface adapters
 */
public class CourseRegisterController {
    private final CRegisterInputBoundary inputBoundary;

    /**
     * Constructs an instance of a CourseRegisterController with an input boundary
     * @param inputBoundary provides methods for the CourseRegister use case to be called
     */
    public CourseRegisterController(
            CRegisterInputBoundary inputBoundary){
        this.inputBoundary = inputBoundary;
    }

    public CRegisterResponseModel registerCourse(
            String courseName,
            String courseCode) {

        CRegisterRequestModel requestModel = new CRegisterRequestModel(
                courseName,
                courseCode);

        return inputBoundary.registerCourse(requestModel);
    }
}
