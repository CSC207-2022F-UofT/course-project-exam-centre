package uc.course.register;

import entities.Course;
import entities.factories.CourseFactory;

import java.time.LocalDateTime;

/** CourseRegisterInteractor implements the ability to register courses.
 * @layer use cases
 */
public class CourseRegisterInteractor implements CRegisterInputBoundary {
    final CRegisterOutputBoundary presenter;
    final CRegisterDsGateway gateway;
    final CourseFactory courseFactory;

    /** Constructs an instance of CourseRegisterInteractor that contains an OutputBoundary,
     * Gateway and courseFactory
     *
     * @param presenter An output boundary containing methods to update view
     * @param gateway A gateway that provides methods to access persistent data
     * @param courseFactory A course factory that creates course objects
     */
    public CourseRegisterInteractor(
            CRegisterOutputBoundary presenter,
            CRegisterDsGateway gateway,
            CourseFactory courseFactory) {
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