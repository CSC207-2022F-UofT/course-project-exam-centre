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
    private static final String storedEmail = "firstlast@mail.uoftears.ca";
    private static final String storedPassword = ";-;";
    private static final String storedUserId = "ABCD1234";
    private static final String storedFirstName = "First";
    private static final String storedLastName = "Last";

    /** Test that LoginInteractor prepares a success view with the correct output data
     *  and updates the currentUser in the state tracker entity,
     *  given a correct password
     */
    private static LoginDsGateway dsGateway;
    @BeforeClass
    public static void setupBeforeClass(){
        dsGateway = new LoginDsGateway() {
            private final List<String> storedEnrolments = new ArrayList<>();

            @Override
            public boolean verifyLoginCredentials(String email, String password) {
                return email.equals(storedEmail)
                        && password.equals(storedPassword);
            }

            @Override
            public boolean getConnectionStatus() {return true;}

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
                assertEquals(storedUserId, responseModel.getUserId());
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
        assertEquals(storedUserId, user.getId());
        assertTrue(stateTracker.checkIfUserTracked(storedUserId));
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
        assertFalse(stateTracker.checkIfUserTracked(storedUserId));
    }
}