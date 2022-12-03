package driver;

import entities.*;
import entities.factories.*;
import fworks.da.PostgresAccessManager;
import ia.gateways.DatabaseAccessGateway;

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

        // Initialise database access gateway
        try {
            DatabaseAccessGateway dbGateway = new PostgresAccessManager(
                    dbHostname,
                    dbPort,
                    dbUser,
                    dbName,
                    dbPassword,
                    dbSslStatus
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
