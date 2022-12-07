package uc.course.register;

import entities.Course;
import entities.CourseInfo;
import entities.StateTracker;
import entities.factories.CourseFactory;

import javax.swing.plaf.nimbus.State;
import java.time.LocalDateTime;

public class CourseRegisterInteractor implements CRegisterInputBoundary {
    private final CRegisterOutputBoundary presenter;
    private final CRegisterDsGateway gateway;
    private final CourseFactory courseFactory;
    private final StateTracker currentState;

    public CourseRegisterInteractor(
            CRegisterOutputBoundary presenter,
            CRegisterDsGateway gateway,
            CourseFactory courseFactory,
            StateTracker currentState) {
        this.presenter = presenter;
        this.gateway = gateway;
        this.courseFactory = courseFactory;
        this.currentState = currentState;
    }

    @Override
    public CRegisterResponseModel registerCourse(CRegisterRequestModel requestModel) {

        if (!gateway.getConnectionStatus()) {
            return presenter.prepareFailView("Database Connection Failed");
        } else if (gateway.checkIfCourseExists(requestModel.getCourseCode())) {
            return presenter.prepareFailView("Course already exists");
        }

        CRegisterDsRequestModel courseDsModel = new CRegisterDsRequestModel(
                requestModel.getCourseName(),
                requestModel.getCourseCode());

        String courseId = gateway.saveCourse(courseDsModel);



        CourseInfo course = courseFactory.create(
                requestModel.getCourseName(),
                requestModel.getCourseCode(),
                courseId);

        CRegisterResponseModel responseModel = new CRegisterResponseModel(
                course.getId(),
                course.getCourseName(),
                course.getCourseCode()
        );
        return presenter.prepareSuccessView(responseModel);
    }
}
