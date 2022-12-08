package uc.course.updatemembers;

import entities.CommonUser;
import entities.Course;
import entities.StateTracker;
import entities.User;
import entities.factories.*;
import ia.gateways.models.CourseDbResponseModel;
import org.junit.Test;
import uc.course.updatemembers.dbmodels.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

public class UpdateCMembershipInteractorTest {
    /** Test that the UpdateCourseMembershipInteractor prepares a
     * success view when a users course enrollments are successfully updated.
     */
    @Test
    public void updateCourseMembershipSuccess() {
        UpdateCMemDsGateway dsGateway = new UpdateCMemDsGateway() {
            @Override
            public boolean checkIfCourseExists(String courseId) {

                return true;
            }

            @Override
            public void addCourseEnrolment(String courseId, String userId) {

            }
            @Override
            public void removeCourseEnrolment(String courseId, String userId) {

            }
            @Override
            public List<String> getCourseIdsByUserId(String userId) {
                ArrayList<String> courseIds = new ArrayList<>();

                return courseIds;
            }

            @Override
            public UpdateCMemUserDbModel getUserById(String userId) {
                return null;
            }

            @Override
            public boolean getConnectionStatus() {
                return false;
            }

            @Override
            public List<? extends UpdateCMemTestDocDbModel> getTestDocsByCourseId(String courseId) {
                return new ArrayList<>();
            }

            @Override
            public List<? extends UpdateCMemSolutionDocDbModel> getSolutionDocsByTestId(String testId) {
                return new ArrayList<>();
            }

            @Override
            public List<? extends UpdateCMemMessageDbModel> getMessagesByParentId(String parentId) {
                return new ArrayList<>();
            }

            @Override
            public UpdateCMemCourseDbModel getCourseById(String courseId) {
                return new CourseDbResponseModel("1234ABCD", "CSC236", "Theory of Computation");
            }
        };



        StateTracker currentState = new StateTracker();
        UserFactory userFactory = new UserFactory();
        CourseFactory courseFactory = new CourseFactory();
        TestDocFactory testDocFactory = new TestDocFactory();
        SolutionDocFactory solutionDocFactory = new SolutionDocFactory();
        MessageFactory messageFactory = new MessageFactory();
        UpdateCMemOutputBoundary presenter = new UpdateCMemOutputBoundary() {
            @Override
            public UpdateCMemResponseModel prepareSuccessView(UpdateCMemResponseModel responseModel) {
                // Check that the right user model is used.
                assertEquals("ABCD1234", responseModel.getCurrentUserModel().getUserId());
                // Check that the right course model is used.
                assertEquals("CSC236", responseModel.getUsersCourseModels().get("1234ABCD").getCourseCode());
                assertTrue(currentState.checkIfUserTracked("ABCD1234"));
                assertTrue(currentState.checkIfCourseTracked("1234ABCD"));
                return null;
            }
            @Override
            public UpdateCMemResponseModel prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected.");
                return null;
            }
        };
    User currentUser = new CommonUser("First", "Last", "first.last@mail.utoronto.ca",
            "ABCD1234");
        currentState.setCurrentUser(currentUser);
        UpdateCourseMembershipInteractor interactor = new UpdateCourseMembershipInteractor(
                dsGateway,
                presenter,
                currentState,
                userFactory,
                courseFactory,
                testDocFactory,
                solutionDocFactory,
                messageFactory);

        // Add the new courses to be enrolled to.
        ArrayList<String> newCourses = new ArrayList<>() {
            {

                add("1234ABCD"); // CourseID for CSC236

            }
        };

        UpdateCMemRequestModel requestModel = new UpdateCMemRequestModel(newCourses);
        interactor.updateCourseMembership(requestModel);

    }

    /** Test that UpdateCourseMembershipInteractor
     * prepares a fail view if the course the user is trying to enroll in is not registered yet.
     */
    @Test
    public void updateCourseMembershipFailCourseNotRegistered() {
        UpdateCMemDsGateway dsGateway = new UpdateCMemDsGateway() {
            @Override
            public boolean checkIfCourseExists(String courseId) {

                return false;
            }

            @Override
            public void addCourseEnrolment(String courseId, String userId) {

            }
            @Override
            public void removeCourseEnrolment(String courseId, String userId) {

            }
            @Override
            public List<String> getCourseIdsByUserId(String userId) {
                ArrayList<String> courseIds = new ArrayList<>();

                return courseIds;
            }

            @Override
            public UpdateCMemUserDbModel getUserById(String userId) {
                return null;
            }

            @Override
            public boolean getConnectionStatus() {
                return false;
            }

            @Override
            public List<? extends UpdateCMemTestDocDbModel> getTestDocsByCourseId(String courseId) {
                return null;
            }

            @Override
            public List<? extends UpdateCMemSolutionDocDbModel> getSolutionDocsByTestId(String testId) {
                return null;
            }

            @Override
            public List<? extends UpdateCMemMessageDbModel> getMessagesByParentId(String parentId) {
                return null;
            }

            @Override
            public UpdateCMemCourseDbModel getCourseById(String courseId) {
                return new CourseDbResponseModel("Theory of Computation", "CSC236", "1234ABCD");
            }
        };



        StateTracker currentState = new StateTracker();
        UserFactory userFactory = new UserFactory();
        CourseFactory courseFactory = new CourseFactory();
        TestDocFactory testDocFactory = new TestDocFactory();
        SolutionDocFactory solutionDocFactory = new SolutionDocFactory();
        MessageFactory messageFactory = new MessageFactory();

        UpdateCMemOutputBoundary presenter = new UpdateCMemOutputBoundary() {
            @Override
            public UpdateCMemResponseModel prepareSuccessView(UpdateCMemResponseModel responseModel) {
                fail("Use case success is unexpected");
                return null;
            }

            @Override
            public UpdateCMemResponseModel prepareFailView(String errorMessage) {
                assertEquals("1234ABCD has not been registered", errorMessage);
                assertTrue(currentState.checkIfUserTracked("ABCD1234"));
                return null;
            }
        };
        User currentUser = new CommonUser("First", "Last",
                "first.last@mail.utoronto.ca", "ABCD1234");
        currentState.setCurrentUser(currentUser);
        UpdateCourseMembershipInteractor interactor = new UpdateCourseMembershipInteractor(
                dsGateway,
                presenter,
                currentState,
                userFactory,
                courseFactory,
                testDocFactory,
                solutionDocFactory,
                messageFactory);

        ArrayList<String> newCourses = new ArrayList<>() {
            {
                add("1234ABCD");
            }
        };

        UpdateCMemRequestModel requestModel = new UpdateCMemRequestModel(newCourses);
        interactor.updateCourseMembership(requestModel);
    }

}
