package driver;

import entities.*;
import fworks.views.*;
/**
 * A driver for testing the UI
 */
public class MainTest {
    public static void main(String[] args) {
        // TODO: is it okay to construct things here?

        StateTracker stateTracker = new StateTracker();
        UserFactory userFactory = new UserFactory(); // TODO: no need to construct if static

        //TODO: This code is problematic in a number of ways. Focus should be on the Main driver for now and when that
        // completed, we can return to this and make a test version of the driver.

//        LoginDsGateway loginDsGateway = new LoginDsGateway(); // TODO
//        LoginPresenter loginPresenter = new LoginPresenter();
//        LoginInteractor loginInteractor = new LoginInteractor(stateTracker, loginDsGateway,
//                loginPresenter, userFactory);
//        LoginController loginController = new LoginController(loginInteractor);
//
//        UserRegisterDsGateway userRegisterDsGateway = new UserRegisterDsGateway(); // TODO
//        UserRegisterResponseFormatter userRegisterResponseFormatter = new UserRegisterResponseFormatter();
//        UserRegisterInteractor userRegisterInteractor = new UserRegisterInteractor(stateTracker, userRegisterDsGateway,
//                userRegisterResponseFormatter, userFactory); // TODO
//        UserRegisterController userRegisterController = new UserRegisterController(userRegisterInteractor);

//        new WelcomeDialog(loginController, userRegisterController); // Register/Log-in window
        new TestFrame(); // View test window
//        new SolutionFrame(); // View solutions window
    }
}
