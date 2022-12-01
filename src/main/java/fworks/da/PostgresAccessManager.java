package fworks.da;

import ia.gateways.DatabaseAccessGateway;
import java.sql.*;
import java.util.*;

/**
 * The PostgresAccessManager class is responsible for implementing the DatabaseAccessGateway
 * interface in the interface adapters layer. The construction of this class establishes a
 * connection to the database belonging to an instance of postgres 14.5. This class implements
 * all database query methods defined in the DatabaseAccessGateway.
 *
 * @layer drivers and frameworks
 */
public class PostgresAccessManager implements DatabaseAccessGateway {

    private final Connection db;

    /** Constructs a new instance of PostgresAccessManager by attempting to establish
     * a connection to Postgres 14.5 database using the given connection data.
     *
     * @param hostname          the hostname of the postgres instance
     * @param port              the port used by the postgres instance
     * @param psqlUser          the user to be used in the postgres connection
     * @param dbName            the name of the DB to be connected to in the postgres
     *                          instance
     * @param psqlPassword      the password to be used in the postgres connection
     * @param sslStatus         whether SSL should be used for the postgres connection
     * @throws SQLException     thrown if connection fails
     */
    public PostgresAccessManager(
            String hostname,
            Integer port,
            String psqlUser,
            String dbName,
            String psqlPassword,
            Boolean sslStatus) throws SQLException {
        String url = "jdbc:postgresql://" + hostname + ":" + port + "/" + dbName;
        Properties props = new Properties();
        props.setProperty("user", psqlUser);
        props.setProperty("password", psqlPassword);
        props.setProperty("ssl", String.valueOf(sslStatus));
        this.db = DriverManager.getConnection(url, props);
    }

    // Utility methods

    /** Checks whether connection to postgres instance is
     *
     * @return boolean representing whether database is connected
     * @throws SQLException if connection check query fails
     */
    public boolean getConnectionStatus() throws SQLException {
        return this.db.isValid(2);
    }

    /** Counts number of rows represented in a given ResultSet object.
     *
     * @param rs                a ResultSet object
     * @return                  an integer representing the number of rows
     * @throws SQLException     if ResultSet query fails
     */
    private int countResultSetRows(ResultSet rs) throws SQLException {
        int numRows = 0;
        while (rs.next()) {
            numRows++;
        }
        return numRows;
    }

    /** Generates a List<String> representation of a given ResultSet row.
     *
     * @param rs        a ResultSet object
     * @return          a List<String> representation of a ResultSet row
     */
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

    // Implemented DatabaseAccessGateway methods

    /** Queries postgres DB to verify if the inputted hashed password matches
     * the saved hashed password corresponding to a user's email.
     *
     * @param email             the unique email address of the user being queried
     * @param hashedPassword    the hashed password to be checked
     *
     * @return whether the inputted hashed password matches the one stored for the user
     * in the postgres DB
     */
    @Override
    public boolean verifyLoginCredentials(String email, String hashedPassword) {
        String remoteHashedPassword = null;
        String query = "SELECT password FROM ec.users WHERE email='" + email + "';";
        String clientHashedPassword = hashPassword(hashedPassword);
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

    /** Queries postgres DB to check whether a course exists by ID.
     *
     * @param courseId the unique ID of the course being checked
     * @return boolean representing whether course exists
     */
    @Override
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

    /** Queries postgres DB to check whether a user exists by email.
     *
     * @param email the email of the user being checked
     * @return boolean representing whether user exists
     */
    @Override
    public boolean checkIfUserExistsByEmailQuery(String email) {
        String query = "SELECT * FROM ec.users WHERE email='" + email + "';";
        try (Statement statement = db.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            int numRows = countResultSetRows(rs);
            return (numRows > 0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /** Queries postgres DB to save data for new course entity.
     *
     * @param courseId          the course ID of the course entity being saved
     * @param courseCode        the course code of the course entity being saved
     * @param courseName        the course name of the course entity being saved
     */
    @Override
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

    /** Queries postgres DB to save data for new user entity.
     *
     * @param userId             the user ID of the user entity being saved
     * @param email              the email of the user entity being saved
     * @param firstName          the first name of the user entity being saved
     * @param lastName           the last name of the user entity being saved
     * @param hashedPassword     the hashed password of the user entity being saved
     */
    @Override
    public void saveUserQuery(
            String userId,
            String email,
            String firstName,
            String lastName,
            String hashedPassword){

        String query = "INSERT INTO ec.users(user_id, email, " +
                "first_name, last_name, password)" +
                " VALUES ('" + userId + "', '" + email +"', '" +
                 firstName + "', '" + lastName + "', '" +
                hashedPassword + "');";

        try (Statement statement = db.createStatement()) {
            statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /** Queries postgres DB to get all course IDs.
     *
     * @return list containing course ID strings
     */
    @Override
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

    /** Queries postgres DB to get a string representing course entity data.
     *
     * @param inputId the unique ID of the course being queried
     * @return list containing course entity data
     */
    @Override
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

    /** Queries postgres DB to get a string representing user entity data.
     *
     * @param inputId the unique ID of the user being queried
     * @return list containing user entity data
     */
    @Override
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

    /** Queries postgres DB to get a string representing user entity data.
     *
     * @param inputEmail the email of the user being queried
     * @return list containing user entity data
     */
    @Override
    public List<String> getUserByEmailQuery(String inputEmail){
        List<String> userData = new ArrayList<>();

        String query = "SELECT * FROM ec.users " +
                "WHERE email='" + inputEmail + "';";

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

    /** Queries postgres DB to get a list containing the course IDs
     * of courses that the user belongs to.
     *
     * @param inputId the unique ID of the user being queried
     * @return list containing course IDs
     */
    @Override
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

    /** Queries postgres DB to get a list containing lists of strings that
     * represent each message entity that shares the given parent ID.
     *
     * @param inputId the parent message ID shared by the messages being requested
     * @return list containing lists of strings representing message entities
     */
    @Override
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

    /** Queries postgres DB to get a list containing lists of strings that
     * represent each test document entity that shares the given course ID.
     *
     * @param inputId the course ID shared by the requested test document entities
     * @return list containing lists of strings representing test document entities
     */
    @Override
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

    /** Queries postgres DB to get a list containing lists of strings that
     * represent each solution document entity that shares the given test ID.
     *
     * @param inputId the test ID shared by the requested solution document entities
     * @return list containing lists of strings representing solution document entities
     */
    @Override
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

    /** Queries postgres DB to save data for a new course enrolment.
     *
     * @param enrolmentId     the enrolment ID of the enrolment being saved
     * @param courseId        the course ID of the enrolment being saved
     * @param userId          the user ID of the enrolment being saved
     */
    @Override
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

    /** Queries postgres DB to delete a course enrolment using a unique course ID
     * and user ID pair.
     *
     * @param courseId        the course ID of the enrolment being deleted
     * @param userId          the user ID of the enrolment being deleted
     */
    @Override
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

    /** Queries postgres DB to get the unique test ID corresponding to the given
     * solution ID.
     *
     * @param inputId the solution ID of the solution document being queried
     * @return a string representing a unique test ID
     */
    @Override
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

    /** Queries postgres DB to get the unique course ID corresponding to the
     * given test ID.
     *
     * @param inputId the test ID of the test document being queried
     * @return a string representing a unique course ID
     */
    @Override
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

    /** Queries postgres DB to save data for new message entity.
     *
     * @param messageId     the message ID of the message entity being saved
     * @param solutionId    the solution ID of the message entity being saved
     * @param userId        the user ID of the message entity being saved
     * @param parentId      the parent ID of the message entity being saved
     * @param messageBody   the message body of the message entity being saved
     */
    @Override
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

    /** Queries postgres DB to save data for new solution document entity.
     *
     * @param solutionId    the solution ID of the solution document being saved
     * @param testId        the test ID of the solution document being saved
     * @param userId        the user ID of the solution document being saved
     * @param voteTotal     the vote total of the solution document being saved
     * @param recordedScore the recorded score of the solution document being saved
     * @param estimatedTime the estimated time of the solution document being saved
     * @param rootMessageId the root message ID of the solution document being saved
     * @param solutionName  the name of the solution document being saved
     */
    @Override
    public void saveSolutionDocumentQuery(
            String solutionId,
            String testId,
            String userId,
            Integer voteTotal,
            Float recordedScore,
            Float estimatedTime,
            String rootMessageId,
            String solutionName) {

        String query = "INSERT INTO ec.solutions(solution_id, " +
                "test_id, user_id, vote_total, recorded_score, " +
                "estimated_time, root_message_id, solution_name)" +
                " VALUES ('" + solutionId + "', '" +
                testId + "', '" + userId + "', '" + voteTotal.toString()
                + "', '" + recordedScore.toString() + "', " +
                "'" + estimatedTime.toString() + "', '" + rootMessageId + "', "
                + "'" + solutionName + "');";

        try (Statement statement = db.createStatement()) {
            statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /** Queries postgres DB to update the root message ID of a given solution
     * document.
     *
     * @param solutionId        the solution ID of the solution document being
     *                          queried
     * @param rootMessageId     the root message ID of the solution document
     *                          being queried
     */
    @Override
    public void updateRootMessageIdOfSolutionQuery(
            String solutionId,
            String rootMessageId) {

        String query = "UPDATE ec.solutions SET root_message_id='"
        + rootMessageId + "' WHERE solution_id='" + solutionId + "';";

        try (Statement statement = db.createStatement()) {
            statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /** Queries postgres DB to save data for new test document entity.
     *
     * @param testId            the test ID of the test document being saved
     * @param userId            the user ID of the test document being saved
     * @param courseId          the course ID of the test document being saved
     * @param testType          the test type of the test document being saved
     * @param numOfQuestions    the number of questions in the test document being saved
     * @param estimatedTime     the estimated time of the test document being saved
     * @param testName          the name of the test document being saved
     */
    @Override
    public void saveTestDocumentQuery(
            String testId,
            String userId,
            String courseId,
            String testType,
            Integer numOfQuestions,
            Float estimatedTime,
            String testName) {

        String query = "INSERT INTO ec.tests(test_id, " +
                "user_id, course_id, test_type, number_of_questions, " +
                "estimated_time, test_name)" +
                " VALUES ('" + testId + "', '" +
                userId + "', '" + courseId + "', '" + testType
                + "', '" + numOfQuestions.toString() + "', " +
                "'" + estimatedTime.toString() + "', '" + testName + "');";

        try (Statement statement = db.createStatement()) {
            statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /** Queries postgres DB to verify if the inputted hashed password matches the
     * saved hashed password corresponding to a user's email.
     *
     * @param email             the unique email address of the user being queried
     * @param hashedPassword    the hash of the inputted user password being checked
     *
     * @return whether the inputted hashed password matches the one stored for the user
     * in the database
     */
    @Override
    public boolean verifyLoginCredentialsQuery(
            String email,
            String hashedPassword
    ) {
        String query = "SELECT * FROM ec.users WHERE email='" + email + "';";
        try (Statement statement = db.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            rs.next();
            return hashedPassword.equals(
                    rs.getString("password")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
