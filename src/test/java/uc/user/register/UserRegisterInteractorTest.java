package uc.user.register;

import entities.factories.UserFactory;
import entities.StateTracker;
import entities.User;
import org.junit.Before;
import org.junit.Test;

import javax.swing.plaf.nimbus.State;

import static org.junit.Assert.*;

public class UserRegisterInteractorTest {

    @Test
    public void registerSuccess() {
        // Assert if a register was successful and the user was saved properly.

        URegisterRequestModel requestModel = new URegisterRequestModel("John", "Doe",
                "email@email.com", "password", "password");
        URegisterDsGateway userDsGateway = new URegisterDsGateway() {
            /* Initializing a URegisterDsGateway that states that a user does not exist in the db
            and that the user info was saved.
            */

            @Override
            public boolean checkIfUserExistsByEmail(String email) {
                return false;
            }

            @Override
            public String saveUser(URegisterDsRequestModel requestModel) {
                return "ABCD1234";
            }
        };

        URegisterOutputBoundary userOutputBoundary = new URegisterOutputBoundary() {
            // Asserting if the register was a success.

            @Override
            public URegisterResponseModel prepareSuccessView(URegisterResponseModel responseModel) {
                // Check if the userId is created and is of length 8
                assertEquals(responseModel.getUserId().length(), 8);
                // Check if loginStatus is true
                assertEquals(responseModel.getLoginStatus(), true);

                return null;
            }

            @Override
            public URegisterResponseModel prepareFailView(String error) {
                // In case of failure
                fail("Use case failure is unexpected.");
                return null;
            }
        };
        StateTracker stateTracker = new StateTracker();
        UserFactory userFactory = new UserFactory();

        UserRegisterInteractor interactor = new UserRegisterInteractor(stateTracker,
                userDsGateway,
                userOutputBoundary,
                userFactory);

        interactor.registerUser(requestModel);
    }
    @Test
    public void registerFailGivenUserExists(){
        // Asserting if the register was a fail because the user exists.

        URegisterRequestModel requestModel = new URegisterRequestModel("John", "Doe",
                "email@email.com", "password", "password");
        URegisterDsGateway userDsGateway = new URegisterDsGateway() {
            @Override
            public boolean checkIfUserExistsByEmail(String email) {
                return true;
            }

            @Override
            public String saveUser(URegisterDsRequestModel requestModel) {
                fail("Use case success is unexpected.");
                return null;
            }
        };

        URegisterOutputBoundary userOutputBoundary = new URegisterOutputBoundary() {
            @Override
            public URegisterResponseModel prepareSuccessView(URegisterResponseModel user) {
                // In case of failure
                fail("Use case success is unexpected.");
                return null;
            }

            @Override
            public URegisterResponseModel prepareFailView(String error) {
                assertTrue(userDsGateway.checkIfUserExistsByEmail("email@email.com"));
                return null;
            }
        };
        StateTracker stateTracker = new StateTracker();
        UserFactory userFactory = new UserFactory();

        UserRegisterInteractor interactor = new UserRegisterInteractor(stateTracker,
                userDsGateway,
                userOutputBoundary,
                userFactory);
        interactor.registerUser(requestModel);
    }

    @Test
    public void registerFailGivenPasswordsDontMatch(){
        // Asserting if the register was a fail because passwords don't match

        URegisterRequestModel requestModel = new URegisterRequestModel("John", "Doe",
                "email@email.com", "password", "notpassword");
        URegisterDsGateway userDsGateway = new URegisterDsGateway() {
            @Override
            public boolean checkIfUserExistsByEmail(String email) {
                return false;
            }

            @Override
            public String saveUser(URegisterDsRequestModel requestModel) {
                fail("Use case success is unexpected");
                return null;
            }
        };

        URegisterOutputBoundary userOutputBoundary = new URegisterOutputBoundary() {
            @Override
            public URegisterResponseModel prepareSuccessView(URegisterResponseModel user) {
                // In case of unexpected success
                fail("Use case success is unexpected.");
                return null;
            }

            @Override
            public URegisterResponseModel prepareFailView(String error) {
                assertNotEquals(requestModel.getPassword(), requestModel.getRepeatPassword());
                return null;
            }
        };

        StateTracker stateTracker = new StateTracker();
        UserFactory userFactory = new UserFactory();

        UserRegisterInteractor interactor = new UserRegisterInteractor(stateTracker,
                userDsGateway,
                userOutputBoundary,
                userFactory);

        interactor.registerUser(requestModel);
    }
}