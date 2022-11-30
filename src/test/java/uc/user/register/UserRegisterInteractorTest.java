package uc.user.register;

import entities.User;
import entities.UserFactory;
import org.junit.Before;
import org.junit.Test;

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
            *
            * */

            @Override
            public boolean checkIfUserExistsByEmail(String email) {
                return false;
            }

            @Override
            public boolean saveNewUserInfo(URegisterDsRequestModel requestModel) {
                return true;
            }

            @Override
            public boolean checkIfUserExists(String userId) {
                return false;
            }
        };

        URegisterOutputBoundary userOutputBoundary = new URegisterOutputBoundary() {
            // Asserting if the register was a success.

            @Override
            public URegisterResponseModel prepareSuccessView(URegisterResponseModel user) {
                // Check if the user's first name matches
                assertEquals(user.getUser().getFirstName(), "John");
                // Check if the user's last name matches
                assertEquals(user.getUser().getLastName(), "Doe");
                // Check if the email matches
                assertEquals(user.getUser().getEmail(), "email@email.com");
                // Check if the userId is created and is of length 9
                assertEquals((user.getUser().getId()).length(), 8);
                // Check if the timestamp is not empty
                assertNotNull(user.timestamp);

                return null;
            };

            @Override
            public URegisterResponseModel prepareFailView(String error) {
                // In case of failure
                fail("Use case failure is unexpected.");
                return null;
            }
        };

        UserFactory userFactory = new UserFactory();

        UserRegisterInteractor interactor = new UserRegisterInteractor(userDsGateway,
                userOutputBoundary, userFactory);

        interactor.registerUser(requestModel);
    }
    @Test
    public void userExists(){
        // Asserting if the register was a fail because the user exists.

        URegisterRequestModel requestModel = new URegisterRequestModel("John", "Doe",
                "email@email.com", "password", "password");
        URegisterDsGateway userDsGateway = new URegisterDsGateway() {
            @Override
            public boolean checkIfUserExistsByEmail(String email) {
                return true;
            }

            @Override
            public boolean saveNewUserInfo(URegisterDsRequestModel requestModel) {
                return false;
            }

            @Override
            public boolean checkIfUserExists(String userId) {
                return false;
            }
        };

        URegisterOutputBoundary userOutputBoundary = new URegisterOutputBoundary() {
            @Override
            public URegisterResponseModel prepareSuccessView(URegisterResponseModel user) {
                // In case of failure
                fail("Use case failure is unexpected.");
                return null;
            }

            @Override
            public URegisterResponseModel prepareFailView(String error) {
                assertTrue(userDsGateway.checkIfUserExistsByEmail("email@email.com"));
                return null;
            }
        };

        UserFactory userFactory = new UserFactory();

        UserRegisterInteractor interactor = new UserRegisterInteractor(userDsGateway,
                userOutputBoundary, userFactory);

        interactor.registerUser(requestModel);
    }

    @Test
    public void passwordsDontMatch(){
        // Asserting if the register was a fail because passwords dont match

        URegisterRequestModel requestModel = new URegisterRequestModel("John", "Doe",
                "email@email.com", "password", "notpassword");
        URegisterDsGateway userDsGateway = new URegisterDsGateway() {
            @Override
            public boolean checkIfUserExistsByEmail(String email) {
                return false;
            }

            @Override
            public boolean saveNewUserInfo(URegisterDsRequestModel requestModel) {
                return false;
            }

            @Override
            public boolean checkIfUserExists(String userId) {
                return false;
            }
        };

        URegisterOutputBoundary userOutputBoundary = new URegisterOutputBoundary() {
            @Override
            public URegisterResponseModel prepareSuccessView(URegisterResponseModel user) {
                // In case of failure
                fail("Use case failure is unexpected.");
                return null;
            }

            @Override
            public URegisterResponseModel prepareFailView(String error) {
                assertNotEquals(requestModel.getPassword(), requestModel.getRepeatPassword());
                return null;
            }
        };

        UserFactory userFactory = new UserFactory();

        UserRegisterInteractor interactor = new UserRegisterInteractor(userDsGateway,
                userOutputBoundary, userFactory);

        interactor.registerUser(requestModel);
    }






}