package uc.course.register;

import entities.Course;
import entities.CourseFactory;

import java.time.LocalDateTime;

/** CourseRegisterInteractor implements the ability to register courses.
 * @layer use cases
 */
public class CourseRegisterInteractor implements CRegisterlnputBoundary {
    final CRegisterPresenter presenter;
    final CRegisterDsGateway gateway;
    final CourseFactory courseFactory;

    /** Constructs an instance of CourseRegisterInteractor that contains an OutputBoundary,
     * Gateway and courseFactory
     *
     * @param presenter
     * @param gateway
     * @param courseFactory
     */
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
        Course course = courseFactory.create(requestModel.getCourseName(), requestModel.getCourseCode(), requestModel.getCourseId());
        CRegisterDsRequestModel courseDsModel = new CRegisterDsRequestModel(course.getCourseName(), course.getCourseCode());
        gateway.saveCourse(courseDsModel);

        LocalDateTime now = LocalDateTime.now();
        CRegisterResponseModel responseModel = new CRegisterResponseModel(course.getCourseName(), true, now.toString());
        return presenter.prepareSuccessView(responseModel);
    }
}
