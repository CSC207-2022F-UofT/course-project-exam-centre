package uc.state.update;

import org.junit.BeforeClass;
import org.junit.Test;
import uc.state.update.dbmodels.*;

import java.util.List;

import static org.junit.Assert.*;

public class UpdateStateInteractorTest {
    private static UpdateStateTestData testData;

    @BeforeClass
    public static void setupBeforeClass(){
        testData = new UpdateStateTestData();
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
        };

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