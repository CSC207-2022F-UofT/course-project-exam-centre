package usecases.user.logout;

import entities.StateTracker;
import entities.User;
import entities.factories.UserFactory;
import iadapters.exceptions.LogoutFailed;
import org.junit.Test;

import static org.junit.Assert.*;

public class LogoutInteractorTest {
    /**
     * Test that logOut removes currentUser from the state tracker instance
     */
    @Test
    public void logOutRemovesCurrentUser(){
        LogoutOutputBoundary presenter = new LogoutOutputBoundary() {
            @Override
            public LogoutResponseModel prepareSuccessView(LogoutResponseModel responseModel) {
                return responseModel;
            }
            @Override
            public LogoutResponseModel prepareFailView(String errorMessage) {
                throw new LogoutFailed(errorMessage);
            }
        };
        StateTracker stateTracker = new StateTracker();
        UserFactory userFactory = new UserFactory();
        User user = userFactory.create("First",
                                       "Last",
                                       "FirstLast@mail.uoftears.ca",
                                       "LOL123");

        stateTracker.setCurrentUser(user);

        // run the use case
        LogoutInteractor interactor = new LogoutInteractor(presenter, stateTracker);
        interactor.logOut();

        assertNull(stateTracker.getCurrentUser());
    }
}