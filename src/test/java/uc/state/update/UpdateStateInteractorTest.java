package uc.state.update;

import entities.SolutionDocument;
import entities.User;
import entities.factories.*;
import org.junit.BeforeClass;
import org.junit.Test;
import uc.state.update.dbmodels.*;
import uc.state.update.responsemodels.*;

import java.util.List;

import static org.junit.Assert.*;

public class UpdateStateInteractorTest {

    private static UserFactory userFactory;
    private static CourseFactory courseFactory;
    private static TestDocFactory testFactory;
    private static SolutionDocFactory solutionFactory;
    private static MessageFactory messageFactory;
    private static UpdateStateUserDbModel userDbResponseModel;
    private static UpdateStateCourseDbModel courseDbResponseModel;
    private static UpdateStateTestDocDbModel testDbResponseModel;
    private static UpdateStateSolutionDocDbModel solutionDbResponseModel;
    private static UpdateStateMessageDbModel messageDbResponseModel;
    private static List<String> storedAllCourseIds;
    private static List<String> storedUsersCourseIds;


    @BeforeClass
    public static void setupBeforeClass(){
        userFactory = new UserFactory();
        courseFactory = new CourseFactory();
        testFactory =  new TestDocFactory();
        solutionFactory = new SolutionDocFactory();
        messageFactory = new MessageFactory();

        userDbResponseModel = new UpdateStateUserDbModel() {
            @Override
            public String getUserId() {
                return null;
            }

            @Override
            public String getFirstName() {
                return null;
            }

            @Override
            public String getLastName() {
                return null;
            }

            @Override
            public String getEmail() {
                return null;
            }
        }

    }

    public void updateStateSuccessGivenUserNull(){
        UpdateStateDsGateway dsGateway = new UpdateStateDsGateway() {
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
                return null;
            }

            @Override
            public UpdateStateUserDbModel getUserById(String userId) {
                return null;
            }

            @Override
            public UpdateStateCourseDbModel getCourseById(String courseId) {
                return null;
            }

            @Override
            public List<String> getAllCourseIds() {
                return null;
            }

            @Override
            public List<String> getCourseIdsByUserId(String userId) {
                return null;
            }

            @Override
            public boolean getConnectionStatus() {
                return false;
            }
        }

        UpdateStateOutputBoundary presenter = new UpdateStateOutputBoundary() {
            @Override
            public UpdateStateResponseModel prepareSuccessView(UpdateStateResponseModel responseModel) {
                // TODO: check responseModel
                return null;
            }

            @Override
            public UpdateStateResponseModel prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected.");
                return null;
            }
        };

        UpdateStateInteractor interactor = new UpdateStateInteractor();
    }

    @Test
    public void updateStateSuccessGivenUserNotNull(){

        UpdateStateInteractor interactor = new UpdateStateInteractor();
    }


    @Test
    public void updateStateSuccessGivenDbConnectionSuccess(){

        UpdateStateInteractor interactor = new UpdateStateInteractor();
    }

    @Test
    public void updateStateFailGivenDbConnectionFail(){
        UpdateStateInteractor interactor = new UpdateStateInteractor();
    }
}