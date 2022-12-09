package uc.course.register;

import entities.CourseInfo;
import entities.StateTracker;
import entities.factories.CourseFactory;

/** CourseRegisterInteractor implements the ability to register courses.
 * @layer use cases
 */
public class CourseRegisterInteractor implements CRegisterInputBoundary {
    private final CRegisterOutputBoundary presenter;
    private final CRegisterDsGateway gateway;
    private final CourseFactory courseFactory;
    private final StateTracker currentState;

    /** Constructs an instance of CourseRegisterInteractor that contains an OutputBoundary,
     * Gateway and courseFactory
     *
     * @param presenter An output boundary containing methods to update view
     * @param gateway A gateway that provides methods to access persistent data
     * @param courseFactory A course factory that creates course objects
     * @param currentState tracks the current state
     */
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

    /** Register a course
     *
     * @param requestModel request model containing information on the course to be registered
     * @return register response model if registration is successful
     */
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
                course.getCourseCode(),
                course.getCourseName()
        );
        return presenter.prepareSuccessView(responseModel);
    }
}