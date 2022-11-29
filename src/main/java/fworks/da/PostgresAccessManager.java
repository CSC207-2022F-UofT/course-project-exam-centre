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
    // TODO: Retrieve variables from request model
    public boolean saveNewTest(){
        String testId = randomIdGenerator();
        String userId = null;
        String courseId = null;
        String testType = null;
        int numberOfQuestions = 0;
        float estimatedTime = 0;
        Timestamp timestamp = null;
        String testName = "";

        String query = "INSERT INTO ec.tests VALUES ('" + testId + "', '" + 
        userId + "', '" + courseId + "', '" + testType + "', '" + numberOfQuestions + 
        "', '" + estimatedTime + "', '" + timestamp + "', '" + testName + "';";
        try (Statement statement = db.createStatement()) {
            statement.executeQuery(query);
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Save new solution in solutions table, to be called after a successful solution file upload
    // TODO: Retrieve variables from request model
    public boolean saveNewSolution(){
        String solutionId = randomIdGenerator();
        String testId = null;
        String userId = null;
        int voteTotal = 0;
        float recordedScore = 0;
        float estimatedTime = 0;
        char rootMessageId = '\u0000';
        Timestamp timestamp = null;
        String solutionName = "";
        
        String query = "INSERT INTO ec.tests VALUES ('" + solutionId + "', '" + 
        testId + "', '" + userId + "', '" + voteTotal + "', '" + recordedScore + 
        "', '" + estimatedTime + "', '" + rootMessageId + "', '" + 
        timestamp + "', '" + solutionName + "';";
        try (Statement statement = db.createStatement()) {
            statement.executeQuery(query);
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Get all tests for a course
    public ArrayList<String> getAllTests(String courseId){
        ArrayList<String> testIdList = new ArrayList<>();
        String query = "SELECT test_id FROM ec.tests WHERE COURSE_ID=" + "', '" + courseId + "';";
        try (Statement statement = db.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String testId = rs.getString("test_id");
                testIdList.add(testId);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return testIdList;
    }

    // Get all solutions for a test
    public ArrayList<String> getAllSolutions(String testId){
        ArrayList<String> solutionIdList = new ArrayList<>();
        String query = "SELECT solution_id FROM ec.solutions WHERE test_id=" + "', '" + testId + "';";
        try (Statement statement = db.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String solutionId = rs.getString("solution_id");
                solutionIdList.add(solutionId);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return solutionIdList;
    }
}
