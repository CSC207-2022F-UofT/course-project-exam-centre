package uc.user.login;

import entities.StateTracker;
import entities.User;
import entities.factories.UserFactory;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class LoginInteractorTest {

    /** Test that LoginInteractor prepares a success view with the correct output data
     *  and updates the currentUser in the state tracker entity,
     *  given a correct password
     */
    private static LoginDsGateway dsGateway;
    @BeforeClass
    public static void setupBeforeClass(){
        dsGateway = new LoginDsGateway() {
            // implement LoginDsGateway using anonymous class
            private final String storedEmail = "firstlast@mail.uoftears.ca";
            private final String storedPassword = ";-;";
            private final String storedUserId = "ABCD1234";
            private final String storedFirstName = "First";
            private final String storedLastName = "Last";
            private final List<String> storedEnrolments = new ArrayList<>();

            @Override
            public boolean verifyLoginCredentials(String email, String password) {
                return email.equals(storedEmail)
                        && password.equals(storedPassword);
            }

            @Override
            public List<String> getCourseIdsByUserId(String userId) {
                return storedEnrolments;
            }

            @Override
            public LoginDsResponseModel getUserByEmail(String email) {
                // implement LoginDsResponseModel using anonymous class
                return new LoginDsResponseModel() {
                    @Override
                    public String getUserId() {
                        return storedUserId;
                    }

                    @Override
                    public String getEmail() {
                        return storedEmail;
                    }

                    @Override
                    public String getFirstName() {
                        return storedFirstName;
                    }

                    @Override
                    public String getLastName() {
                        return storedLastName;
                    }
                };
            }
        };
    }

    @Test
    public void logInSuccess() {

        LoginOutputBoundary presenter = new LoginOutputBoundary() {
            @Override
            public LoginResponseModel prepareSuccessView(LoginResponseModel responseModel) {
                assertEquals("ABCD1234", responseModel.getUserId());
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
        LoginInteractor interactor = new LoginInteractor(stateTracker, dsGateway, presenter, userFactory);

        // run the use case, supposing the user input matches stored data
        LoginRequestModel requestModel = new LoginRequestModel("firstlast@mail.uoftears.ca", ";-;");
        interactor.logIn(requestModel);

        // check if current user is tracked
        assertNotNull(stateTracker.getCurrentUser());
        User user = stateTracker.getCurrentUser();
        assertEquals("ABCD1234", user.getId());
        assertTrue(stateTracker.checkIfUserTracked("ABCD1234"));
    }

    /** Test that LoginInteractor prepares a fail view
     *  and leaves the currentUser null in the state tracker entity,
     *  given an incorrect password
     */
    @Test
    public void logInFailGivenIncorrectPassword() {
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
        LoginInteractor interactor = new LoginInteractor(stateTracker, dsGateway, presenter, userFactory);

        // run the use case, supposing the user inputted password does not match
        LoginRequestModel requestModel = new LoginRequestModel("firstlast@mail.uoftears.ca", ":(");
        interactor.logIn(requestModel);

        // check if current user is tracked
        assertNull(stateTracker.getCurrentUser());
        assertFalse(stateTracker.checkIfUserTracked("ABCD1234"));
    }
}