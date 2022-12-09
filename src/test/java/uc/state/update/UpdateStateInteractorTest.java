package uc.state.update;

import entities.Course;
import entities.StateTracker;
import entities.factories.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import uc.state.update.dbmodels.*;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class UpdateStateInteractorTest {
    private static UpdateStateTestData testData;
    private static UserFactory userFactory;
    private static CourseFactory courseFactory;
    private static TestDocFactory testFactory;
    private static SolutionDocFactory solutionFactory;
    private static MessageFactory messageFactory;
    private StateTracker currentState;

    @BeforeClass
    public static void setupBeforeClass(){
        testData = new UpdateStateTestData();

        userFactory = new UserFactory();
        courseFactory = new CourseFactory();
        testFactory = new TestDocFactory();
        solutionFactory = new SolutionDocFactory();
        messageFactory = new MessageFactory();
    }

    @Before
    public void setup(){
        currentState = new StateTracker();
    }
    /**
     * Test that update state successfully reloads all courses tracked in state
     * Given successful connection and null user
     */
    @Test
    public void updateStateReloadsAllCoursesNullUser(){
        UpdateStateDsGateway dsGateway = new UpdateStateDsGateway() {
            final boolean connectionStatus = true;
            @Override
            public List<? extends UpdateStateTestDocDbModel> getTestDocsByCourseId(String courseId) {
                return null;
            }

            @Override
            public List<? extends UpdateStateSolutionDocDbModel> getSolutionDocsByTestId(String testId) {
                return null;
            }

            @Override
            public List<? extends UpdateStateMessageDbModel> getMessagesByParentId(String parentId) {
                return testData.getStoredMessagesByParentId(parentId);
            }

            @Override
            public UpdateStateUserDbModel getUserById(String userId) {
                return testData.getUserDbResponseModelById(userId);
            }

            @Override
            public UpdateStateCourseDbModel getCourseById(String courseId) {
                return testData.getCourseDbResponseModelById(courseId);
            }

            @Override
            public List<String> getAllCourseIds() {
                return testData.getStoredAllCourseIds();
            }

            @Override
            public List<String> getCourseIdsByUserId(String userId) {
                return testData.getStoredCourseIdsByUserId(userId);
            }

            @Override
            public boolean getConnectionStatus() {
                return connectionStatus;
            }
        };

        UpdateStateOutputBoundary presenter = new UpdateStateOutputBoundary() {
            @Override
            public UpdateStateResponseModel prepareSuccessView(UpdateStateResponseModel responseModel) {
                // check that the presenter does not display any changes
                assertNull(responseModel.getCurrentUserModel());
                assertTrue(responseModel.getCourseInfoModels().isEmpty());
                assertTrue(responseModel.getCurrentUserCourseModels().isEmpty());
                return null;
            }

            @Override
            public UpdateStateResponseModel prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected.");
                return null;
            }
        };
        UpdateStateInteractor interactor = new UpdateStateInteractor(presenter,
                                                                     dsGateway,
                                                                     currentState,
                                                                     userFactory,
                                                                     courseFactory,
                                                                     testFactory,
                                                                     solutionFactory,
                                                                     messageFactory);
        interactor.updateState();
        Map<String, Course> trackedCourses = currentState.getAllTrackedCourses();
        List<String> storedAllCourseIds = testData.getStoredAllCourseIds();
        assertEquals(storedAllCourseIds.size(), trackedCourses.size());
    }

//    @Test
//    public void updateStateSuccessGivenUserNotNullConnectionSuccess(){
//
//        UpdateStateInteractor interactor = new UpdateStateInteractor();
//    }
//
//
//    @Test
//    public void updateStateFailGivenDbConnectionFail(){
//        UpdateStateInteractor interactor = new UpdateStateInteractor();
//    }
}