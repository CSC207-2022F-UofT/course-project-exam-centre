package uc.course.register;

import entities.Course;
import entities.factories.CourseFactory;

import java.time.LocalDateTime;

public class CourseRegisterInteractor implements CRegisterInputBoundary {
    final CRegisterPresenter presenter;
    final CRegisterDsGateway gateway;
    final CourseFactory courseFactory;

    public CourseRegisterInteractor(CRegisterPresenter presenter, CRegisterDsGateway gateway, CourseFactory courseFactory) {
        this.presenter = presenter;
        this.gateway = gateway;
        this.courseFactory = courseFactory;
    }

    @Override
    public CRegisterResponseModel registerCourse(CRegisterRequestModel requestModel) {
        if (gateway.checkIfCourseExists(requestModel.getCourseCode())) {
            return presenter.prepareFailView("Course already exists");
        }
        CRegisterDsRequestModel courseDsModel = new CRegisterDsRequestModel(
                requestModel.getCourseName(),
                requestModel.getCourseCode());

        String courseId = gateway.saveCourse(courseDsModel);

        Course course = courseFactory.create(
                requestModel.getCourseName(),
                requestModel.getCourseCode(),
                courseId);

        LocalDateTime now = LocalDateTime.now();
        CRegisterResponseModel responseModel = new CRegisterResponseModel(course.getCourseName(), true, now.toString());
        return presenter.prepareSuccessView(responseModel);
    }
}
