package uc.user.login;

import entities.User;
import entities.UserFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginInteractorTest {
    @Test
    void logIn() {
        LoginDsGateway loginDsGateway = new LoginDsGateway() {
            @Override
            public boolean verifyLoginCredentials(String email, String password) {
                return true;
            }
        };

        UserFactory userFactory = new UserFactory() {
            @Override
            public User create(String firstName, String lastName, String email, int userId) {
                return null;
            }
        };

    }

}
