package driver;

import entities.*;
import fworks.da.FtpAccessManager;
import fworks.da.PostgresAccessManager;
import fworks.views.TestFrame;
import fworks.views.WelcomeDialog;
import ia.gateways.DatabaseAccessGateway;
import ia.gateways.FileAccessGateway;
import uc.course.register.CRegisterInputBoundary;
import uc.course.register.CourseRegisterInteractor;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {

        Properties config = new Properties();

        // Welcome Message :D
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
                ftpRemotePath
            );

            // Construct entity factories
            //TODO: make factory methods non-static
            CourseFactory courseFactory             = new CourseFactory();
            MessageFactory messageFactory           = new MessageFactory();
            MessageTreeFactory messageTreeFactory   = new MessageTreeFactory();
            SolutionDocFactory solutionDocFactory   = new SolutionDocFactory();
            TestDocFactory testDocFactory           = new TestDocFactory();
            StateTrackerFactory stateTrackerFactory = new StateTrackerFactory();
            UserFactory userFactory                 = new UserFactory();

            // Construct state tracker entity
            StateTracker currentState = stateTrackerFactory.create();

            // Construct use case presenters



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
