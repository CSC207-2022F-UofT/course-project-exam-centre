package fworks.da;

import ia.gateways.DatabaseAccessGateway;
import uc.user.register.URegisterDsRequestModel;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.*;

public class PostgresAccessManager implements DatabaseAccessGateway {

    private Connection db;
    private boolean connectionStatus;

    public PostgresAccessManager(String hostname, Integer port, String psqlUser, String dbName,
                                 String psqlPassword, Boolean sslStatus) throws SQLException {
        String url = "jdbc:postgresql://" + hostname + ":" + port + "/" + dbName;
        Properties props = new Properties();
        props.setProperty("user", psqlUser);
        props.setProperty("password", psqlPassword);
        props.setProperty("ssl", String.valueOf(sslStatus));
        this.db = DriverManager.getConnection(url, props);
        this.connectionStatus = this.db.isValid(2);
    }

    public boolean getConnectionStatus() {
        return connectionStatus;
    }

    private static String hashPassword(String unhashedPassword)
    {
        try {

            MessageDigest messDigest = MessageDigest.getInstance("SHA-512");

            byte[] messDigestBytes = messDigest.digest(unhashedPassword.getBytes());
            BigInteger signumRepresentation = new BigInteger(1, messDigestBytes);
            String hashedPassword = signumRepresentation.toString(16);

            while (hashedPassword.length() < 32) {
                hashedPassword = "0" + hashedPassword;
            }
            return hashedPassword;
        }

        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static int countResultSetRows(ResultSet rs) throws SQLException {
        int numRows = 0;
        while (rs.next()) {
            numRows++;
        }
        return numRows;
    }

    public boolean verifyLoginCredentials(String email, String password) {
        String remoteHashedPassword = null;
        String query = "SELECT password FROM ec.users WHERE email='" + email + "';";
        String clientHashedPassword = hashPassword(password);
        try (Statement statement = db.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                remoteHashedPassword = rs.getString("password");
            }
            return remoteHashedPassword.equals(clientHashedPassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getUserId(String email) {
        String userId = null;
        String query = "SELECT user_id FROM ec.users WHERE email='" + email + "';";
        try (Statement statement = db.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                userId = rs.getString("user_id");
            }
            return userId;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkIfCourseExists(String courseId) {
        String query = "SELECT * FROM ec.courses WHERE course_id='" + courseId + "';";
        try (Statement statement = db.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            int numRows = countResultSetRows(rs);
            return (numRows > 0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkIfUserExists(String userId) {
        String query = "SELECT * FROM ec.user WHERE user_id='" + userId + "';";
        try (Statement statement = db.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            int numRows = countResultSetRows(rs);
            return (numRows > 0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkIfUserExistsByEmail(String email) {
        String query = "SELECT * FROM ec.user WHERE email='" + email + "';";
        try (Statement statement = db.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            int numRows = countResultSetRows(rs);
            return (numRows > 0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean saveNewUserInfo(URegisterDsRequestModel requestModel) {
        String email = requestModel.getEmail();
        String userId = requestModel.getUserId();
        String hashedPassword = hashPassword(requestModel.getPassword());
        String firstName = requestModel.getFirstName();
        String lastName = requestModel.getLastName();

        String query = "INSERT INTO ec.users(user_id, email, first_name, last_name, password)" +
                " VALUES ('" + userId + "', '" + email + "', '" + firstName + "', '" + lastName
                + "', '" + hashedPassword + "';";
        try (Statement statement = db.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Generate random alphanumeric ID
    public static String randomIdGenerator(){
        UUID randomUUID = UUID.randomUUID();
        return randomUUID.toString().substring(0,8).replaceAll("-", "");
    }

    // Save new test in tests table, to be called after a successful test file upload
    public boolean saveNewTest(){
        String test_id = randomIdGenerator();
        String user_id = null;
        String course_id = null;
        String test_type = null;
        int number_of_questions = 0;
        int estimated_time = 0;

        String query = "INSERT INTO ec.tests VALUES ('" + test_id + "', '" + 
        user_id + "', '" + course_id + "', '" + test_type + "', '" + 
        number_of_questions + "', '" + estimated_time + "';";
        try (Statement statement = db.createStatement()) {
            statement.executeQuery(query);
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Save new solution in solutions table, to be called after a successful solution file upload
    public boolean saveNewSolution(){
        String solution_id = randomIdGenerator();
        String test_id = null;
        String user_id = null;
        int vote_total = 0;
        int recorded_score = 0;
        int estimated_time = 0;
        char root_message_id = '\u0000';
        
        String query = "INSERT INTO ec.tests VALUES ('" + solution_id + "', '" + 
        test_id + "', '" + user_id + "', '" + vote_total + "', '" + recorded_score + 
        "', '" + estimated_time + "', '" + root_message_id + "';";
        try (Statement statement = db.createStatement()) {
            statement.executeQuery(query);
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
