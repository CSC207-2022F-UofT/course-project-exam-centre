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

    private int countResultSetRows(ResultSet rs) throws SQLException {
        int numRows = 0;
        while (rs.next()) {
            numRows++;
        }
        return numRows;
    }

    private List<String> getCurrentResultSetStringRow(ResultSet rs) {
        try{
            int numOfColumns = rs.getMetaData().getColumnCount();
            List<String> stringRow = new ArrayList<>();
            for (int i = 0; i < numOfColumns; i++){
                stringRow.add(
                        rs.getString(i)
                );
            }
            return stringRow;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
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
            assert remoteHashedPassword != null;
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

    public boolean checkIfCourseExistsQuery(String courseId) {
        String query = "SELECT * FROM ec.courses WHERE course_id='" + courseId + "';";
        try (Statement statement = db.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            int numRows = countResultSetRows(rs);
            return (numRows > 0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkIfUserExistsQuery(String userId) {
        String query = "SELECT * FROM ec.user WHERE user_id='" + userId + "';";
        try (Statement statement = db.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            int numRows = countResultSetRows(rs);
            return (numRows > 0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkIfUserExistsByEmailQuery(String email) {
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

    // Save new test in tests table, to be called after a successful test file upload
    // TODO: Retrieve variables from request model
    public boolean saveNewTest(){
        String testId = generateRandomId();
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
        String solutionId = generateRandomId();
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

    public void saveCourseQuery(String courseId,
                                String courseCode,
                                String courseName){

        String query = "INSERT INTO ec.courses(course_id, code, name)" +
                " VALUES ('" + courseId + "', '" +
                courseCode + "', '" + courseName + "');";

        try (Statement statement = db.createStatement()) {
            statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getAllCourseIdsQuery(){
        List<String> courseIds = new ArrayList<>();

        String query = "SELECT course_id FROM ec.courses;";

        try (Statement statement = db.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String courseId = rs.getString("course_id");
                courseIds.add(courseId);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return courseIds;
    }

    public List<String> getCourseByIdQuery(String inputId){
        List<String> courseData = new ArrayList<>();

        String query = "SELECT * FROM ec.courses " +
                "WHERE course_id='" + inputId + "';";

        try (Statement statement = db.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            rs.next();

            String courseId = rs.getString("course_id");
            String courseCode = rs.getString("code");
            String courseName = rs.getString("name");

            courseData.add(courseId);
            courseData.add(courseCode);
            courseData.add(courseName);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return courseData;
    }

    public List<String> getUserByIdQuery(String inputId){
        List<String> userData = new ArrayList<>();

        String query = "SELECT * FROM ec.users " +
                "WHERE user_id='" + inputId + "';";

        try (Statement statement = db.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            rs.next();

            String userId = rs.getString("user_id");
            String email = rs.getString("email");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");

            userData.add(userId);
            userData.add(email);
            userData.add(firstName);
            userData.add(lastName);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userData;
    }

    public List<String> getCourseIdsByUserIdQuery(String inputId){
        List<String> courseIds = new ArrayList<>();

        String query = "SELECT * FROM ec.enrolments " +
                "WHERE user_id='" + inputId + "';";

        try (Statement statement = db.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String courseId = rs.getString("course_id");
                courseIds.add(courseId);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return courseIds;
    }

    public List<List<String>> getMessagesByParentIdQuery(String inputId){
        List<List<String>> messagesData = new ArrayList<>();

        String query = "SELECT * FROM ec.messages " +
                "WHERE parent_id='" + inputId + "';";

        try (Statement statement = db.createStatement()) {
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()) {
                messagesData.add(
                        getCurrentResultSetStringRow(rs)
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return messagesData;
    }

    public List<List<String>> getTestDocsByCourseIdQuery(String inputId){
        List<List<String>> testsData = new ArrayList<>();

        String query = "SELECT * FROM ec.tests " +
                "WHERE course_id='" + inputId + "';";

        try (Statement statement = db.createStatement()) {
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()) {
                testsData.add(
                        getCurrentResultSetStringRow(rs)
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return testsData;
    }

    public List<List<String>> getSolutionDocsByTestIdQuery(String inputId){
        List<List<String>> solutionsData = new ArrayList<>();

        String query = "SELECT * FROM ec.solutions " +
                "WHERE test_id='" + inputId + "';";

        try (Statement statement = db.createStatement()) {
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()) {
                solutionsData.add(
                        getCurrentResultSetStringRow(rs)
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return solutionsData;
    }

    public void addCourseEnrolmentQuery(
            String enrolmentId,
            String courseId,
            String userId) {

        String query = "INSERT INTO ec.enrolments(enrolment_id, " +
                "user_id, course_id)" +
                " VALUES ('" + enrolmentId + "', '" +
                userId + "', '" + courseId + "');";

        try (Statement statement = db.createStatement()) {
            statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeCourseEnrolmentQuery(
            String courseId,
            String userId) {

        String query = "DELETE FROM ec.enrolments " +
                "WHERE user_id='" + userId + "' AND " +
                "course_id='" + courseId + "';";

        try (Statement statement = db.createStatement()) {
            statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getTestIdBySolutionIdQuery(String inputId){

        String testId;
        String query = "SELECT test_id FROM ec.solutions " +
                "WHERE solution_id='" + inputId + "';";

        try (Statement statement = db.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            rs.next();

            testId = rs.getString("test_id");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return testId;
    }

    public String getCourseIdByTestIdQuery(String inputId){

        String courseId;
        String query = "SELECT course_id FROM ec.tests " +
                "WHERE test_id='" + inputId + "';";

        try (Statement statement = db.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            rs.next();

            courseId = rs.getString("course_id");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return courseId;
    }

    public void addMessageQuery(String messageId,
                                String solutionId,
                                String userId,
                                String parentId,
                                String messageBody) {
        String query = "INSERT INTO ec.messages(message_id, " +
                "solution_id, user_id, parent_id, body)" +
                " VALUES ('" + messageId + "', '" +
                solutionId + "', '" + userId + "', '" + parentId
                + "', '" + messageBody + "');";

        try (Statement statement = db.createStatement()) {
            statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
