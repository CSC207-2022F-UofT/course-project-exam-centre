package driver;

import fworks.views.SolutionFrame;
import fworks.views.TestFrame;
import fworks.views.WelcomeDialog;

/**
 * A driver for testing the UI
 */
public class MainTest {
    public static void main(String[] args) {
        new WelcomeDialog(); // Register/Log-in window
        new TestFrame(); // View test window
//        new SolutionFrame(); // View solutions window
    }
}
