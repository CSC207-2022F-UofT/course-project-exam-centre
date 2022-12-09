package driver;

import entities.*;
import entities.factories.*;
import frameworks.daccess.FtpAccessManager;
import frameworks.daccess.PostgresAccessManager;
import frameworks.views.*;
import iadapters.viewmodels.Updatable;
import iadapters.gateways.ViewManagerGateway;
import iadapters.controllers.*;
import iadapters.gateways.DatabaseAccessGateway;
import iadapters.gateways.FileAccessGateway;
import iadapters.presenters.*;
import iadapters.viewmodels.MainViewModel;
import usecases.course.register.CRegisterInputBoundary;
import usecases.course.register.CRegisterOutputBoundary;
import usecases.course.register.CourseRegisterInteractor;
import usecases.course.updatemembers.UpdateCMemInputBoundary;
import usecases.course.updatemembers.UpdateCMemOutputBoundary;
import usecases.course.updatemembers.UpdateCourseMembershipInteractor;
import usecases.submessage.SubDBMessInputBoundary;
import usecases.submessage.SubDBMessOutputBoundary;
import usecases.submessage.SubmitDBMessageInteractor;
import usecases.doc.downloaddoc.DownloadDocInputBoundary;
import usecases.doc.downloaddoc.DownloadDocInteractor;
import usecases.doc.downloaddoc.DownloadDocOutputBoundary;
import usecases.doc.submitsolution.SubmitSDocOutputBoundary;
import usecases.doc.submitsolution.SubmitSDocInputBoundary;
import usecases.doc.submitsolution.SubmitSolutionDocInteractor;
import usecases.doc.submittest.SubmitTDocInputBoundary;
import usecases.doc.submittest.SubmitTDocOutputBoundary;
import usecases.doc.submittest.SubmitTestDocInteractor;
import usecases.doc.voteonsolution.VoteOnSolutionDocInteractor;
import usecases.doc.voteonsolution.VoteSDocInputBoundary;
import usecases.doc.voteonsolution.VoteSDocOutputBoundary;
import usecases.state.update.UpdateStateInputBoundary;
import usecases.state.update.UpdateStateInteractor;
import usecases.state.update.UpdateStateOutputBoundary;
import usecases.user.login.LoginInputBoundary;
import usecases.user.login.LoginInteractor;
import usecases.user.login.LoginOutputBoundary;
import usecases.user.logout.LogoutInputBoundary;
import usecases.user.logout.LogoutInteractor;
import usecases.user.logout.LogoutOutputBoundary;
import usecases.user.register.URegisterInputBoundary;
import usecases.user.register.URegisterOutputBoundary;
import usecases.user.register.UserRegisterInteractor;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {

        Properties config = new Properties();

        // Welcome Message :D
        System.out.println("\n====    U of T Exam Centre     ===");
        System.out.println("====  CSC 207 Course Project   ===\n\n");


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

            // Construct main view model
            MainViewModel mainViewModel = new MainViewModel();

            // Construct a new ViewManager
            ViewManagerGateway viewManagerGateway = new ViewManager();

            // Construct use case presenters
            CRegisterOutputBoundary courseRegisterPresenter
                    = new CourseRegisterPresenter(viewManagerGateway, mainViewModel);
            LoginOutputBoundary loginPresenter
                    = new LoginPresenter(viewManagerGateway, mainViewModel);
            LogoutOutputBoundary logoutPresenter
                    = new LogoutPresenter(viewManagerGateway, mainViewModel);
            SubDBMessOutputBoundary submitDBMessagePresenter
                    = new SubmitDBMessagePresenter(viewManagerGateway, mainViewModel);
            SubmitSDocOutputBoundary submitSolutionDocPresenter
                    = new SubmitSolutionDocPresenter(viewManagerGateway, mainViewModel);
            SubmitTDocOutputBoundary submitTestDocPresenter
                    = new SubmitTestDocPresenter(viewManagerGateway, mainViewModel);
            UpdateCMemOutputBoundary updateCourseMembershipPresenter
                    = new UpdateCourseMembershipPresenter(viewManagerGateway, mainViewModel);
            URegisterOutputBoundary userRegisterPresenter
                    = new UserRegisterPresenter(viewManagerGateway, mainViewModel);
            UpdateStateOutputBoundary updateStatePresenter
                    = new UpdateStatePresenter(viewManagerGateway, mainViewModel);
            DownloadDocOutputBoundary downloadDocPresenter
                    = new DownloadDocPresenter(viewManagerGateway, mainViewModel);
            VoteSDocOutputBoundary voteSDocPresenter
                    = new VoteSDocPresenter(viewManagerGateway, mainViewModel);

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

            DownloadDocInputBoundary downloadDocInteractor
                    = new DownloadDocInteractor(
                            fileAccessGateway,
                            downloadDocPresenter,
                            currentState
            );

            VoteSDocInputBoundary voteOnDocInteractor
                    = new VoteOnSolutionDocInteractor(
                            dbGateway,
                            voteSDocPresenter,
                            currentState
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
            DownloadDocController downloadDocController
                    = new DownloadDocController(downloadDocInteractor);
            VoteOnDocSolutionController voteOnDocSolutionController
                    = new VoteOnDocSolutionController(voteOnDocInteractor);

            // Construct views and configure ViewManager references
            ArrayList<Updatable> updatableScreens = new ArrayList<>();
            viewManagerGateway.setUpdateStateController(updateStateController);

            updatableScreens.add(
                    new WelcomeDialog(
                            loginController,
                            userRegisterController,
                            logoutController,
                            mainViewModel,
                            submitTestDocController,
                            submitSolutionDocController,
                            updateCourseMembershipController,
                            downloadDocController,
                            updateStateController,
                            courseRegisterController
                    )
            );

            // Configure view manager references
            viewManagerGateway.setUpdatableViews(updatableScreens);

            // Update current state
            updateStateController.updateState();

        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
