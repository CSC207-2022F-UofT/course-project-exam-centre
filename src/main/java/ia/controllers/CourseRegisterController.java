package ia.controllers;

import uc.course.register.CRegisterInputBoundary;
import uc.course.register.CRegisterRequestModel;
import uc.course.register.CRegisterResponseModel;

public class CourseRegisterController {
    private final CRegisterInputBoundary inputBoundary;

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
