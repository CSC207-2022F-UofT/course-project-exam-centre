package driver;

import entities.*;
import entities.factories.*;
import fworks.da.FtpAccessManager;
import fworks.da.PostgresAccessManager;
import fworks.views.TestFrame;
import fworks.views.Updatable;
import fworks.views.ViewManager;
import ia.gateways.ViewManagerGateway;
import fworks.views.WelcomeDialog;
import ia.controllers.*;
import ia.gateways.DatabaseAccessGateway;
import ia.gateways.FileAccessGateway;
import ia.presenters.*;
import uc.course.register.CRegisterInputBoundary;
import uc.course.register.CRegisterOutputBoundary;
import uc.course.register.CourseRegisterInteractor;
import uc.course.updatemembers.UpdateCMemInputBoundary;
import uc.course.updatemembers.UpdateCMemOutputBoundary;
import uc.course.updatemembers.UpdateCourseMembershipInteractor;
import uc.dboard.submessage.SubDBMessInputBoundary;
import uc.dboard.submessage.SubDBMessOutputBoundary;
import uc.dboard.submessage.SubmitDBMessageInteractor;
import uc.doc.submitsolution.SubmitSDocOutputBoundary;
import uc.doc.submitsolution.SubmitSDocInputBoundary;
import uc.doc.submitsolution.SubmitSolutionDocInteractor;
import uc.doc.submittest.SubmitTDocInputBoundary;
import uc.doc.submittest.SubmitTDocOutputBoundary;
import uc.doc.submittest.SubmitTestDocInteractor;
import uc.state.update.UpdateStateInputBoundary;
import uc.state.update.UpdateStateInteractor;
import uc.state.update.UpdateStateOutputBoundary;
import uc.user.login.LoginInputBoundary;
import uc.user.login.LoginInteractor;
import uc.user.login.LoginOutputBoundary;
import uc.user.logout.LogoutInputBoundary;
import uc.user.logout.LogoutInteractor;
import uc.user.logout.LogoutOutputBoundary;
import uc.user.register.URegisterInputBoundary;
import uc.user.register.URegisterOutputBoundary;
import uc.user.register.UserRegisterInteractor;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {

        Properties config = new Properties();

        // Wel9come Message :D
        System.out.println("\n====    U of T Exam Centre     ===");
        System.out.println("====  CSC 207 Course Project   ===\n\n");

        // Initialise JFrame view
        // new WelcomeDialog();
        // new TestFrame();

        // Load local config file
        try {
            String configFilePath = "src/main/java/config/local.properties";
            FileInputStream propsInput = new FileInputStream(configFilePath);
            config.load(propsInput);
        } catch (IOException e) {
            System.out.println("Failed to load config file");
        }

        // Load config variables
        String dbHostname = System.getenv().getOrDefault(
                "DB_HOST", config.getProperty("DB_HOST"));
        String dbPassword = System.getenv().getOrDefault(
                "DB_PASS", config.getProperty("DB_PASS"));
        Integer dbPort = Integer.parseInt(System.getenv().getOrDefault(
                "DB_PORT", config.getProperty("DB_PORT")));
        String dbName = System.getenv().getOrDefault(
                "DB_NAME", config.getProperty("DB_NAME"));
        Boolean dbSslStatus = Boolean.parseBoolean(System.getenv().getOrDefault(
                "DB_SSL_STATUS", config.getProperty("DB_SSL_STATUS")));
        String dbUser = System.getenv().getOrDefault(
                "DB_USER", config.getProperty("DB_USER"));
        String ftpHost = System.getenv().getOrDefault(
                "FTP_HOST", config.getProperty("FTP_HOST"));
        int ftpPort = Integer.parseInt(System.getenv().getOrDefault(
                "FTP_PORT", config.getProperty("FTP_PORT")));
        String ftpUser = System.getenv().getOrDefault(
                "FTP_USER", config.getProperty("FTP_USER"));
        String ftpPass = System.getenv().getOrDefault(
                "FTP_PASS", config.getProperty("FTP_PASS"));
        String ftpRemotePath = System.getenv().getOrDefault(
                "REMOTE_PATH", config.getProperty("REMOTE_PATH"));
        String ftpLocalPath = System.getenv().getOrDefault(
                "LOCAL_PATH", config.getProperty("LOCAL_PATH"));

        try {
            // Initialise database access gateway
            DatabaseAccessGateway dbGateway = new PostgresAccessManager(
                    dbHostname,
                    dbPort,
                    dbUser,
                    dbName,
                    dbPassword,
                    dbSslStatus
            );

            // Initialise file access gateway
            FileAccessGateway fileAccessGateway = new FtpAccessManager(
                ftpHost,
                ftpPort,
                ftpUser,
                ftpPass,
                ftpRemotePath,
                ftpLocalPath
            );

            // Construct entity factories
            CourseFactory courseFactory             = new CourseFactory();
            MessageFactory messageFactory           = new MessageFactory();
            SolutionDocFactory solutionDocFactory   = new SolutionDocFactory();
            TestDocFactory testDocFactory           = new TestDocFactory();
            StateTrackerFactory stateTrackerFactory = new StateTrackerFactory();
            UserFactory userFactory                 = new UserFactory();

            // Construct state tracker entity
            StateTracker currentState = stateTrackerFactory.create();

            // Construct a new ViewManager
            ArrayList<Updatable> updatableScreens = new ArrayList<>();
            //TODO Add Updatable screens to ArrayList
            ViewManagerGateway viewManagerGateway = new ViewManager(updatableScreens);

            // Construct use case presenters
            CRegisterOutputBoundary courseRegisterPresenter
                    = new CourseRegisterPresenter(viewManagerGateway);
            LoginOutputBoundary loginPresenter
                    = new LoginPresenter(viewManagerGateway);
            LogoutOutputBoundary logoutPresenter
                    = new LogoutPresenter(viewManagerGateway);
            SubDBMessOutputBoundary submitDBMessagePresenter
                    = new SubmitDBMessagePresenter(viewManagerGateway);
            SubmitSDocOutputBoundary submitSolutionDocPresenter
                    = new SubmitSolutionDocPresenter(viewManagerGateway);
            SubmitTDocOutputBoundary submitTestDocPresenter
                    = new SubmitTestDocPresenter(viewManagerGateway);
            UpdateCMemOutputBoundary updateCourseMembershipPresenter
                    = new UpdateCourseMembershipPresenter(viewManagerGateway);
            URegisterOutputBoundary userRegisterPresenter
                    = new UserRegisterPresenter(viewManagerGateway);
            UpdateStateOutputBoundary updateStatePresenter
                    = new UpdateStatePresenter(viewManagerGateway);

            // Construct use case interactors
            CRegisterInputBoundary courseRegisterInteractor
                    = new CourseRegisterInteractor(
                            courseRegisterPresenter,
                            dbGateway,
                            courseFactory,
                            currentState
            );
            LoginInputBoundary loginInteractor
                    = new LoginInteractor(
                            currentState,
                            dbGateway,
                            loginPresenter,
                            userFactory
            );
            LogoutInputBoundary logoutInteractor
                    = new LogoutInteractor(
                    logoutPresenter,
                    currentState
            );
            SubDBMessInputBoundary submitDBMessageInteractor
                    = new SubmitDBMessageInteractor(
                    submitDBMessagePresenter,
                    messageFactory,
                    dbGateway,
                    currentState
            );
            SubmitSDocInputBoundary submitSolutionDocInteractor
                    = new SubmitSolutionDocInteractor(
                    dbGateway,
                    fileAccessGateway,
                    submitSolutionDocPresenter,
                    currentState,
                    solutionDocFactory
            );
            SubmitTDocInputBoundary submitTestDocInteractor
                    = new SubmitTestDocInteractor(
                    dbGateway,
                    fileAccessGateway,
                    submitTestDocPresenter,
                    currentState,
                    testDocFactory
            );
            UpdateCMemInputBoundary updateCourseMembershipInteractor
                    = new UpdateCourseMembershipInteractor(
                    dbGateway,
                    updateCourseMembershipPresenter,
                    currentState,
                    userFactory,
                    courseFactory,
                    testDocFactory,
                    solutionDocFactory,
                    messageFactory
            );
            URegisterInputBoundary userRegisterInteractor
                    = new UserRegisterInteractor(
                            currentState,
                            dbGateway,
                            userRegisterPresenter,
                            userFactory
            );
            UpdateStateInputBoundary updateStateInteractor
                    = new UpdateStateInteractor(
                            updateStatePresenter,
                            dbGateway,
                            currentState,
                            userFactory,
                            courseFactory,
                            testDocFactory,
                            solutionDocFactory,
                            messageFactory
            );

            // Construct use case controllers
            CourseRegisterController courseRegisterController
                    = new CourseRegisterController(courseRegisterInteractor);
            LoginController loginController
                    = new LoginController(loginInteractor);
            SubmitDBMessageController submitDBMesageController
                    = new SubmitDBMessageController(submitDBMessageInteractor);
            SubmitSolutionDocController submitSolutionDocController
                    = new SubmitSolutionDocController(submitSolutionDocInteractor);
            SubmitTestDocController submitTestDocController
                    = new SubmitTestDocController(submitTestDocInteractor);
            UpdateCourseMembershipController updateCourseMembershipController
                    = new UpdateCourseMembershipController(updateCourseMembershipInteractor);
            UpdateStateController updateStateController
                    = new UpdateStateController(updateStateInteractor);
            UserRegisterController userRegisterController
                    = new UserRegisterController(userRegisterInteractor);
            LogoutController logoutController
                    = new LogoutController(logoutInteractor);

            // Update current state
            updateStateController.updateState();

            // Testing Use Cases

//            loginController.logIn(
//                    "harveydonnelly404@gmail.com",
//                    "HelloWorld"
//            );
////            userRegisterController.registerUser(
////                    "Harvey",
////                    "Donnelly",
////                    "harveydonnelly404@gmail.com",
////                    "HelloWorld",
////                    "HelloWorld"
////            );
//            updateStateController.updateState();
//            List<String> testList = new ArrayList<>();
//            testList.add("h9ib1a73");
//            testList.add("koz8t694");
//
//            updateCourseMembershipController.updateCourseMembership(testList);
//
//            submitTestDocController.submitTestDocument(
//                        "Test #1",
//                    5,
//                    Float.parseFloat("1.5"),
//                    "Final Exam",
//                    "h9ib1a73",
//                    "./lib/example.pdf"
//            );

            // Construct JFrame views
//            new WelcomeDialog(
//                    loginController,
//                    userRegisterController
//            );
            new TestFrame(logoutController);

        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
