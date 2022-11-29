package uc.user.login;

import entities.StateTracker;
import entities.User;
import entities.UserFactory;

import org.junit.Test;

import static org.junit.Assert.*;

public class LoginInteractorTest {

    /** Test that LoginInteractor prepares a success view with the correct output data
     *  and updates the currentUser in the state tracker entity,
     *  given a correct password
     */
    @Test
    void logInSuccess() {

        LoginDsGateway dsGateway = new LoginDsGateway() {
            @Override
            public boolean verifyLoginCredentials(String email, String password) {
                // compares to credentials in "DB"
                return email.equals("firstname@mail.uoftears.ca")
                        && password.equals(";-;");
            }

            @Override
            public LoginDsResponseModel getUserInfo(String email) {
                return new LoginDsResponseModel("LOL123", "First", "Last");
            }
        };

        LoginOutputBoundary presenter = new LoginOutputBoundary() {
            @Override
            public LoginResponseModel prepareSuccessView(LoginResponseModel responseModel) {
                assertTrue(responseModel.isLoggedIn());
                assertEquals("LOL123", responseModel.getUserId());
                return null;
            }

            @Override
            public LoginResponseModel prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected.");
                return null;
            }
        };

        UserFactory userFactory = new UserFactory();
        StateTracker stateTracker = new StateTracker();
        LoginInteractor interactor = new LoginInteractor(userFactory, presenter, dsGateway, stateTracker);

        // run the use case, supposing the user input is as follows
        LoginRequestModel requestModel = new LoginRequestModel("firstname@mail.uoftears.ca", ";-;");
        interactor.logIn(requestModel);

        // check if current user is tracked
        assertNotNull(stateTracker.getCurrentUser());
        User user = stateTracker.getCurrentUser();
        assertEquals("LOL123", user.getId());
        assertTrue(stateTracker.checkIfUserTracked("LOL123"));
    }

    /** Test that LoginInteractor prepares a fail view
     *  and leaves the currentUser null in the state tracker entity,
     *  given an incorrect password
     */
    @Test
    void logInFail() {

        LoginDsGateway dsGateway = new LoginDsGateway() {
            @Override
            public boolean verifyLoginCredentials(String email, String password) {
                // compares to credentials in "DB"
                return email.equals("firstname@mail.uoftears.ca")
                        && password.equals(";-;");
            }

            @Override
            public LoginDsResponseModel getUserInfo(String email) {
                return new LoginDsResponseModel("LOL123", "First", "Last");
            }
        };

        LoginOutputBoundary presenter = new LoginOutputBoundary() {
            @Override
            public LoginResponseModel prepareSuccessView(LoginResponseModel responseModel) {
                fail("Use case success is unexpected.");
                return null;
            }

            @Override
            public LoginResponseModel prepareFailView(String errorMessage) {
                return null;
            }
        };

        UserFactory userFactory = new UserFactory();
        StateTracker stateTracker = new StateTracker();
        LoginInteractor interactor = new LoginInteractor(userFactory, presenter, dsGateway, stateTracker);

        // run the use case, supposing the user input is as follows
        LoginRequestModel requestModel = new LoginRequestModel("firstname@mail.uoftears.ca", ":(");
        interactor.logIn(requestModel);

        // check if current user is tracked
        assertNull(stateTracker.getCurrentUser());
        assertFalse(stateTracker.checkIfUserTracked("LOL123"));
    }

}