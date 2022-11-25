package driver;

import fworks.da.PostgresAccessManager;
import ia.gateways.DatabaseAccessGateway;
import fworks.views.MainFrame;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {

        Properties config = new Properties();
        DatabaseAccessGateway db;

        // Welcome Message :D
        System.out.println("\n====    U of T Exam Centre     ===");
        System.out.println("====  CSC 207 Course Project   ===\n\n");

        // Initialise JFrame view
        new MainFrame();

        // Load local config file
        try {
            String configFilePath = "src/main/java/config/local.properties";
            FileInputStream propsInput = new FileInputStream(configFilePath);
            config.load(propsInput);
        } catch (IOException e) {
            System.out.println("Failed to load config file");
        }

        // Load config variables
        String dbHostname = config.getProperty("DB_HOST");
        String dbPassword = config.getProperty("DB_PASS");
        Integer dbPort = Integer.parseInt(config.getProperty("DB_PORT"));
        String dbName = config.getProperty("DB_NAME");
        Boolean dbSslStatus = Boolean.parseBoolean(config.getProperty("DB_SSL_STATUS"));
        String dbUser = config.getProperty("DB_USER");

        // Initialise Database Access Gateway
        try {
            db = new PostgresAccessManager(
                    dbHostname,
                    dbPort,
                    dbUser,
                    dbName,
                    dbPassword,
                    dbSslStatus
            );
            /* TODO: Inject this into controllers */
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
