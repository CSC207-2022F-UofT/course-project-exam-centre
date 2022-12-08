package uc.course.register;

import entities.StateTracker;
import entities.Course;
import entities.factories.CourseFactory;

import org.junit.Test;
import uc.course.updatemembers.UpdateCourseMembershipInteractor;

import static org.junit.Assert.*;
public class CourseRegisterInteractorTest {
    /** Test that the CourseRegisterInteractor prepares a Success view when a
     *  a new course is successfully registered.
     */
    @Test
    public void registerSuccess(){
        CRegisterDsGateway dsGateway = new CRegisterDsGateway() {
            // implement CRegisterDsGateway using anonymous class
            @Override
            public boolean checkIfCourseExists(String identifier) {
                return false; //The course hasn't been registered yet.
            }
            @Override
            public String saveCourse(CRegisterDsRequestModel requestModel) {
                return "12345678";
            }

            @Override
            public boolean getConnectionStatus() {
                return true;
            }
        };

        CRegisterOutputBoundary presenter = new CRegisterOutputBoundary() {
            @Override
            public CRegisterResponseModel prepareSuccessView(CRegisterResponseModel responseModel) {
                assertEquals("CSC236", responseModel.getCourseCode());
                return null;
            }

            @Override
            public CRegisterResponseModel prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected.");
                return null;
            }
        };


        CourseFactory courseFactory = new CourseFactory();
        StateTracker stateTracker = new StateTracker();
        CourseRegisterInteractor interactor = new CourseRegisterInteractor(presenter, dsGateway,
                courseFactory, stateTracker);

        // run the use case.
        CRegisterRequestModel requestModel = new CRegisterRequestModel("Theory of Computation",
                "CSC236");
        interactor.registerCourse(requestModel);

    }

    /** Test that CourseRegisterInteractor prepares a fail view
     * when connection with the database has failed.
     *
     */
    @Test
    public void registerFailNoConnection(){
        CRegisterDsGateway dsGateway = new CRegisterDsGateway() {
            // implement CRegisterDsGateway using anonymous class
            @Override
            public boolean checkIfCourseExists(String identifier) {
                return true; //The course hasn't been registered yet.
            }

            @Override
            public String saveCourse(CRegisterDsRequestModel requestModel) {
                return "12345678";
            }

            @Override
            public boolean getConnectionStatus() {
                return false;
            }
        };

        CRegisterOutputBoundary presenter = new CRegisterOutputBoundary() {
            @Override
            public CRegisterResponseModel prepareSuccessView(CRegisterResponseModel responseModel) {
                fail("Use case success is unexpected.");
                return null;
            }

            @Override
            public CRegisterResponseModel prepareFailView(String errorMessage) {
                assertEquals("Database Connection Failed", errorMessage);
                return null;
            }
        };

        CourseFactory courseFactory = new CourseFactory();
        StateTracker stateTracker = new StateTracker();
        CourseRegisterInteractor interactor = new CourseRegisterInteractor(presenter, dsGateway,
                courseFactory, stateTracker);

        // run the use case.
        CRegisterRequestModel requestModel = new CRegisterRequestModel("Theory of Computation", "CSC236");
        interactor.registerCourse(requestModel);
    }

    /** Test that CourseRegisterInteractor prepares a fail view
     * when a course has already been registered.
     *
     */
    @Test
    public void registerFailCourseAlreadyExists()
    {
        CRegisterDsGateway dsGateway = new CRegisterDsGateway() {
            // implement CRegisterDsGateway using anonymous class
            @Override
            public boolean checkIfCourseExists(String identifier) {
                return true; //The course hasn't been registered yet.
            }

            @Override
            public String saveCourse(CRegisterDsRequestModel requestModel) {
                return "12345678";
            }

            @Override
            public boolean getConnectionStatus() {
                return true;
            }
        };

        CRegisterOutputBoundary presenter = new CRegisterOutputBoundary() {
            @Override
            public CRegisterResponseModel prepareSuccessView(CRegisterResponseModel responseModel) {
                fail("Use case success is unexpected.");
                return null;
            }

            @Override
            public CRegisterResponseModel prepareFailView(String errorMessage) {
                assertEquals("Course already exists", errorMessage);
                return null;
            }
        };

        CourseFactory courseFactory = new CourseFactory();
        StateTracker stateTracker = new StateTracker();
        CourseRegisterInteractor interactor = new CourseRegisterInteractor(presenter, dsGateway,
                courseFactory, stateTracker);

        // run the use case.
        CRegisterRequestModel requestModel = new CRegisterRequestModel("Theory of Computation", "CSC236");
        interactor.registerCourse(requestModel);
    }
}
