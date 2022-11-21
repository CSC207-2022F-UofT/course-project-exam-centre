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

}
