package ia.gateways;

import ia.gateways.models.*;
import uc.course.register.CRegisterDsGateway;
import uc.course.register.CRegisterDsRequestModel;
import uc.course.updatemembers.UpdateCMemDsGateway;
import uc.dboard.submessage.SubDBMessDsGateway;
import uc.dboard.submessage.SubDBMessDsRequestModel;
import uc.doc.submitsolution.SubSDocDsGateway;
import uc.doc.submitsolution.SubSDocDsRequestModel;
import uc.doc.submittest.SubTDocDsGateway;
import uc.doc.submittest.SubTDocDsRequestModel;
import uc.doc.voteonsolution.VoteSDocDsGateway;
import uc.doc.voteonsolution.VoteSDocDsRequestModel;
import uc.state.update.UpdateStateDsGateway;
import uc.user.login.LoginDsGateway;
import uc.user.register.URegisterDsGateway;
import uc.user.register.URegisterDsRequestModel;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The DatabaseAccessGateway interface is responsible for defining the database query methods
 * that must be implemented for all use cases and implementing all use case specific DS gateway
 * methods (by extending all use case specific DS gateway interfaces).
 *
 * This interface defines default methods which implement the use case specific DS gateway
 * interfaces by parsing the input data, formatting the response data and calling the
 * appropriate query method. This includes generating and assigning unique entity IDs, and
 * hashing passwords where necessary.
 *
 * All database query methods expect parsed inputs and are expected to return list/string matrix
 * representations of the raw data stored in the database or simple boolean and string values
 * where appropriate. The structure of the raw data matrices are determined by the database
 * schema which should be followed by any concrete database implementation.
 *
 * @layer interface adapters
 */
public interface DatabaseAccessGateway
        extends UpdateStateDsGateway,
        CRegisterDsGateway,
        UpdateCMemDsGateway,
        SubDBMessDsGateway,
        SubSDocDsGateway,
        SubTDocDsGateway,
        LoginDsGateway,
        URegisterDsGateway,
        VoteSDocDsGateway {

    // Query methods to be implemented by a concrete database
    // access manager class in Drivers and Frameworks layer.

    /** Checks whether gateway is connected to database.
     *
     * @return boolean representing whether database is connected
     * @throws Exception if connection check query fails
     */
    boolean getConnectionStatus() throws Exception;

    /** Queries database to check whether a course exists by ID.
     *
     * @param courseId the unique ID of the course being checked
     * @return boolean representing whether course exists
     */
    boolean checkIfCourseExistsQuery(String courseId);

    /** Queries database to check whether a user exists by email.
     *
     * @param email the email of the user being checked
     * @return boolean representing whether user exists
     */
    boolean checkIfUserExistsByEmailQuery(String email);

    /** Queries database to get all course IDs.
     *
     * @return list containing course ID strings
     */
    List<String> getAllCourseIdsQuery();

    /** Queries database to get a string representing course entity data.
     *
     * @param courseId the unique ID of the course being queried
     * @return list containing course entity data
     */
    List<String> getCourseByIdQuery(String courseId);

    /** Queries database to get a string representing user entity data.
     *
     * @param userId the unique ID of the user being queried
     * @return list containing user entity data
     */
    List<String> getUserByIdQuery(String userId);

    /** Queries database to get a string representing user entity data.
     *
     * @param email the email of the user being queried
     * @return list containing user entity data
     */
    List<String> getUserByEmailQuery(String email);

    /** Queries database to get a list containing the course IDs
     * of courses that the user belongs to.
     *
     * @param userId the unique ID of the user being queried
     * @return list containing course IDs
     */
    List<String> getCourseIdsByUserIdQuery(String userId);

    /** Queries database to get a list containing lists of strings that represent
     * each message entity that shares the given parent ID.
     *
     * @param parentId the parent message ID shared by the messages being requested
     * @return list containing lists of strings representing message entities
     */
    List<List<String>> getMessagesByParentIdQuery(String parentId);

    /** Queries database to get a list containing lists of strings that represent
     * each test document entity that shares the given course ID.
     *
     * @param courseId the course ID shared by the requested test document entities
     * @return list containing lists of strings representing test document entities
     */
    List<List<String>> getTestDocsByCourseIdQuery(String courseId);

    /** Queries database to get a list containing lists of strings that represent
     * each solution document entity that shares the given test ID.
     *
     * @param testId the test ID shared by the requested solution document entities
     * @return list containing lists of strings representing solution document entities
     */
    List<List<String>> getSolutionDocsByTestIdQuery(String testId);

    /** Queries database to get the unique test ID corresponding to the given solution ID.
     *
     * @param solutionId the solution ID of the solution document being queried
     * @return a string representing a unique test ID
     */
    String getTestIdBySolutionIdQuery(String solutionId);

    /** Queries database to get the unique course ID corresponding to the given test ID.
     *
     * @param testId the test ID of the test document being queried
     * @return a string representing a unique course ID
     */
    String getCourseIdByTestIdQuery(String testId);

    /** Queries database to get the total votes of a solution document
     *  corresponding to the given solution ID.
     *
     * @param solutionId the solution ID of the solution document being queried
     * @return an int representing the total votes of the solution document
     */
    int getVoteTotalBySolutionIdQuery(String solutionId);

    /** Queries database to save data for new solution document entity.
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
    void saveSolutionDocumentQuery(
            String solutionId,
            String testId,
            String userId,
            Integer voteTotal,
            Float recordedScore,
            Float estimatedTime,
            String rootMessageId,
            String solutionName);

    /** Queries database to save data for new test document entity.
     *
     * @param testId            the test ID of the test document being saved
     * @param userId            the user ID of the test document being saved
     * @param courseId          the course ID of the test document being saved
     * @param testType          the test type of the test document being saved
     * @param numOfQuestions    the number of questions in the test document being saved
     * @param estimatedTime     the estimated time of the test document being saved
     * @param testName          the name of the test document being saved
     */
    void saveTestDocumentQuery(
            String testId,
            String userId,
            String courseId,
            String testType,
            Integer numOfQuestions,
            Float estimatedTime,
            String testName);

    /** Queries database to update the root message ID of a given solution document.
     *
     * @param solutionId        the solution ID of the solution document being queried
     * @param rootMessageId     the root message ID of the solution document being queried
     */
    void updateRootMessageIdOfSolutionQuery(
            String solutionId,
            String rootMessageId);

    /** Queries database to save data for new course entity.
     *
     * @param courseId          the course ID of the course entity being saved
     * @param courseCode        the course code of the course entity being saved
     * @param courseName        the course name of the course entity being saved
     */
    void saveCourseQuery(String courseId,
                         String courseCode,
                         String courseName);

    /** Queries database to save data for new user entity.
     *
     * @param userId             the user ID of the user entity being saved
     * @param email              the email of the user entity being saved
     * @param firstName          the first name of the user entity being saved
     * @param lastName           the last name of the user entity being saved
     * @param hashedPassword     the hashed password of the user entity being saved
     */
    void saveUserQuery(
            String userId,
            String email,
            String firstName,
            String lastName,
            String hashedPassword);

    /** Queries database to save data for new message entity.
     *
     * @param messageId     the message ID of the message entity being saved
     * @param solutionId    the solution ID of the message entity being saved
     * @param userId        the user ID of the message entity being saved
     * @param parentId      the parent ID of the message entity being saved
     * @param messageBody   the message body of the message entity being saved
     */
    void addMessageQuery(String messageId,
                         String solutionId,
                         String userId,
                         String parentId,
                         String messageBody);

    /** Queries database to save data for a new course enrolment.
     *
     * @param enrolmentId     the enrolment ID of the enrolment being saved
     * @param courseId        the course ID of the enrolment being saved
     * @param userId          the user ID of the enrolment being saved
     */
    void addCourseEnrolmentQuery(
            String enrolmentId,
            String courseId,
            String userId);

    /** Queries database to delete a course enrolment using a unique course ID
     * and user ID pair.
     *
     * @param courseId        the course ID of the enrolment being deleted
     * @param userId          the user ID of the enrolment being deleted
     */
    void removeCourseEnrolmentQuery(
            String courseId,
            String userId);

    /** Queries database to verify if the inputted hashed password matches the
     * saved hashed password corresponding to a user's email.
     *
     * @param email             the unique email address of the user being queried
     * @param hashedPassword    the hash of the inputted user password being checked
     *
     * @return whether the inputted hashed password matches the one stored for the user
     * in the database
     */
    boolean verifyLoginCredentialsQuery(
            String email,
            String hashedPassword
    );
        
    /** Queries database to update the total number of votes of the solution document.
    *
    * @param solutionId    the solution ID of the solution document to be updated
    * @param voteTotal        the vote total to update the solution document to
    */
   void updateSolutionDocVoteQuery(
           String solutionId,
           int voteTotal);


    // Default methods implementing use case database gateways
    // Note: some methods implement methods across multiple use case gateways

    /** Saves data for new user entity.
     *
     * @param requestModel      the use case DS request model representing the
     *                          data of the user to be saved.
     *
     * @return the unique user ID of the saved user entity
     */
    @Override
    default String saveUser(URegisterDsRequestModel requestModel) {
        String userId = generateRandomId();
        saveUserQuery(
                userId,
                requestModel.getEmail(),
                requestModel.getFirstName(),
                requestModel.getLastName(),
                hashPassword(
                        requestModel.getPassword()
                ));
        return userId;
    }

    /** Gets data representing a user entity corresponding to the given email.
     *
     * @param email      the email of the user entity being requested
     *
     * @return a response model representing the data of a user entity
     */
    @Override
    default UserDbModel getUserByEmail(String email) {
        List<String> rawUserData = getUserByEmailQuery(email);

        return new UserDbModel(
                rawUserData.get(0),         // userId
                rawUserData.get(1),         // email
                rawUserData.get(2),         // firstName
                rawUserData.get(3)          // lastName
        );
    }

    /** Checks whether the hash of the inputted password matches the
     * saved hashed password corresponding to a user's email.
     *
     * @param email             the unique email address of the user being queried
     * @param password          the plaintext of the user password being checked
     *
     * @return whether the hash of the inputted plaintext password matches the one
     * that is saved corresponding to the user entity
     */
    @Override
    default boolean verifyLoginCredentials(
            String email,
            String password) {

        String hashedPassword = hashPassword(password);
        return verifyLoginCredentialsQuery(email, hashedPassword);

    }

    /** Saves data for new solution document entity.
     *
     * @param requestModel      the use case DS request model representing the
     *                          data of the solution document to be saved.
     *
     * @return the unique solution ID of the saved solution document entity
     */
    @Override
    default String saveSolutionDocument(SubSDocDsRequestModel requestModel) {
        String solutionId = generateRandomId();

        saveSolutionDocumentQuery(
                solutionId,
                requestModel.getParentTestId(),
                requestModel.getUserId(),
                requestModel.getVoteTotal(),
                requestModel.getRecordedScore(),
                requestModel.getEstimatedTime(),
                requestModel.getRootMessageId(),
                requestModel.getName()
        );
        return solutionId;
    }

    /** Saves data for new test document entity.
     *
     * @param requestModel      the use case DS request model representing the
     *                          data of the test document to be saved.
     *
     * @return the unique test ID of the saved test document entity
     */
    @Override
    default String saveTestDocument(SubTDocDsRequestModel requestModel) {
        String testId = generateRandomId();

        saveTestDocumentQuery(
                testId,
                requestModel.getUserId(),
                requestModel.getCourseId(),
                requestModel.getTestType(),
                requestModel.getNumberOfQuestions(),
                requestModel.getRecordedTime(),
                requestModel.getName()
        );
        return testId;
    }

    /** Update the root message ID of a given solution document.
     *
     * @param solutionId        the solution ID of the solution document being updated
     * @param rootMessageId     the new root message ID of the solution document
     *                          being updated
     */
    @Override
    default void updateRootMessageIdOfSolution(
            String solutionId,
            String rootMessageId) {

        updateRootMessageIdOfSolutionQuery(
                solutionId,
                rootMessageId);
    }

    /** Saves data for new course enrolment.
     *
     * @param courseId        the course ID of the enrolment being saved
     * @param userId          the user ID of the enrolment being saved
     */
    @Override
    default void addCourseEnrolment(
            String courseId,
            String userId) {

        String enrolmentId = generateRandomId();
        addCourseEnrolmentQuery(
                enrolmentId,
                courseId,
                userId);
    }

    /** Delete a course enrolment using a unique course ID and user ID pair.
     *
     * @param courseId        the course ID of the enrolment being deleted
     * @param userId          the user ID of the enrolment being deleted
     */
    @Override
    default void removeCourseEnrolment(
            String courseId,
            String userId) {

        removeCourseEnrolmentQuery(
                courseId,
                userId);
    }

    /** Saves data for a new root message (no parent ID or body by definition).
     *
     * @param solutionId    the solution ID of the root message being saved
     * @param userId        the user ID of the root message being saved
     *
     * @return the message ID of the saved root message
     */
    @Override
    default String addRootMessage(
            String solutionId,
            String userId) {

        String messageId = generateRandomId();
        String parentId = "";

        addMessageQuery(
                messageId,
                solutionId,
                userId,
                parentId,
                ""
        );
        return messageId;
    }

    /** Save data for new message entity.
     *
     * @param requestModel      the use case DS request model representing the
     *                          data of the new message entity to be saved.
     *
     * @return the unique message ID of the saved message entity
     */
    @Override
    default String addMessage(SubDBMessDsRequestModel requestModel) {

        String messageId = generateRandomId();
        String parentId = requestModel.getParentId();

        if (parentId == null) {
            parentId = "";
        }

        addMessageQuery(
                messageId,
                requestModel.getSolutionId(),
                requestModel.getUserId(),
                parentId,
                requestModel.getBody()
        );

        return messageId;
    }

    /** Get the test ID corresponding to a solution ID.
     *
     * @param solutionId    a solution ID corresponding to the
     *                      requested test ID
     *
     * @return a string representing the requested test ID
     */
    @Override
    default String getTestIdBySolutionId(String solutionId) {
        return getTestIdBySolutionIdQuery(solutionId);
    }

    /** Get the course ID corresponding to a test ID.
     *
     * @param testId        a test ID corresponding to the
     *                      requested course ID
     *
     * @return a string representing the requested course ID
     */
    @Override
    default String getCourseIdByTestId(String testId) {
        return getCourseIdByTestIdQuery(testId);
    }

    /** Check whether a course exists by ID.
     *
     * @param courseId the unique ID of the course being checked
     * @return boolean representing whether course exists
     */
    @Override
    default boolean checkIfCourseExists(String courseId) {
        return checkIfCourseExistsQuery(courseId);
    }

    /** Queries database to check whether a user exists by email.
     *
     * @param email the email of the user being checked
     * @return boolean representing whether user exists
     */
    @Override
    default boolean checkIfUserExistsByEmail(String email) {
        return checkIfUserExistsByEmailQuery(email);
    }

    /** Saves data for new course entity.
     *
     * @param requestModel      the use case DS request model representing the
     *                          data of the course entity to be saved.
     *
     * @return the unique course ID of the course entity
     */
    @Override
    default String saveCourse(CRegisterDsRequestModel requestModel) {
        String courseId = generateRandomId();
        saveCourseQuery(
                courseId,
                requestModel.getCourseCode(),
                requestModel.getCourseName()
                );
        return courseId;
    }

    /** Gets all course IDs
     *
     * @return list containing course ID strings
     */
    @Override
    default List<String> getAllCourseIds() {
        return getAllCourseIdsQuery();
    }

    /** Gets course data by course ID.
     *
     * @param courseId the unique ID of the course being requested
     *
     * @return CourseDbModel object representing the data for the
     * requested course entity
     */
    @Override
    default CourseDbModel getCourseById(String courseId) {
        List<String> rawCourseData = getCourseByIdQuery(courseId);

        return new CourseDbModel(
                rawCourseData.get(0),           // courseId
                rawCourseData.get(1),           // courseCode
                rawCourseData.get(2)            // courseName
        );
    }

    /** Gets user data by user ID.
     *
     * @param userId the unique ID of the user being requested
     *
     * @return UserDbModel object representing the data for the
     * requested user entity
     */
    @Override
    default UserDbModel getUserById(String userId) {
        List<String> rawUserData = getUserByIdQuery(userId);

        return new UserDbModel(
                rawUserData.get(0),         // userId
                rawUserData.get(1),         // email
                rawUserData.get(2),         // firstName
                rawUserData.get(3)          // lastName
        );
    }

    /** Gets a list containing the course IDs of courses that
     * the user belongs to.
     *
     * @param userId the unique ID of the associated user
     * @return list containing strings of course IDs
     */
    @Override
    default List<String> getCourseIdsByUserId(String userId) {
        return getCourseIdsByUserIdQuery(userId);
    }

    /** Gets a list containing model representations of
     * each message entity that shares the given parent ID.
     *
     * @param parentId the parent message ID shared by the messages being requested
     * @return list containing MessageDbModel objects which each represent the data
     * for a message entity
     */
    @Override
    default List<MessageDbModel> getMessagesByParentId(String parentId) {
        List<List<String>> rawMessagesData = getMessagesByParentIdQuery(parentId);
        List<MessageDbModel> response = new ArrayList<>();

        for (List<String> row : rawMessagesData) {
            response.add(
                    new MessageDbModel(
                            row.get(0),                     // messageId
                            row.get(1),                     // solutionId
                            row.get(2),                     // userId
                            row.get(3),                     // parentId
                            row.get(4),                     // messageBody
                            LocalDateTime.parse(row.get(5)) // sentTimestamp
                    )
            );
        }
        return response;
    }

    /** Gets a list containing model representations of
     * each test document entity that shares the given course ID.
     *
     * @param courseId the course ID shared by the requested test document entities
     * @return list containing TestDocDbModel objects which each represent the data
     * for a test document entity
     */
    @Override
    default List<TestDocDbModel> getTestDocsByCourseId(String courseId) {
        List<List<String>> rawTestDocData = getTestDocsByCourseIdQuery(courseId);
        List<TestDocDbModel> response = new ArrayList<>();

        for (List<String> row : rawTestDocData) {
            response.add(
                    new TestDocDbModel(
                            row.get(0),                     // testId
                            row.get(1),                     // userId
                            row.get(2),                     // courseId
                            Integer.parseInt(row.get(3)),   // numOfQuestions
                            Float.parseFloat(row.get(4)),   // estimatedTime
                            row.get(6)                      // testName
                    )
            );
        }
        return response;
    }

    /** Gets a list containing model representations of
     * each solution document entity that shares the given test ID.
     *
     * @param testId the test ID shared by the requested solution document entities
     * @return list containing SolutionDocDbModel objects which each represent the data
     * for a solution document entity
     */
    @Override
    default List<SolutionDocDbModel> getSolutionDocsByTestId(String testId) {
        List<List<String>> rawSolutionDocData = getSolutionDocsByTestIdQuery(testId);
        List<SolutionDocDbModel> response = new ArrayList<>();

        for (List<String> row : rawSolutionDocData) {
            response.add(
                    new SolutionDocDbModel(
                            row.get(0),                     // solutionId
                            row.get(1),                     // testId
                            row.get(2),                     // userId
                            Integer.parseInt(row.get(3)),   // voteTotal
                            Float.parseFloat(row.get(4)),   // recordedScore
                            Float.parseFloat(row.get(5)),   // estimatedTime
                            row.get(6),                     // rootMessageId
                            row.get(8)                      // solutionName
                    )
            );
        }
        return response;
    }

    // Utility methods for gateway requests

    /** Generates a new random entity ID.
     *
     * @return a random 8 digit alphanumeric that is statistically unique
     */
    default String generateRandomId(){
        UUID randomUUID = UUID.randomUUID();
        return randomUUID.toString()
                .substring(0,8)
                .replaceAll("-", "");
    }

    /** Generates a SHA-512 hashed representation of a given plaintext password
     *
     * @param plaintextPassword a plaintext string representation of a password
     *
     * @return string representation of the SHA-512 hash of the inputted
     * plaintext password
     */
    default String hashPassword(String plaintextPassword)
    {
        try {

            MessageDigest messDigest = MessageDigest.getInstance("SHA-512");

            byte[] messDigestBytes = messDigest.digest(plaintextPassword.getBytes());
            BigInteger signumRepresentation = new BigInteger(1, messDigestBytes);
            StringBuilder hashedPassword = new StringBuilder(
                    signumRepresentation.toString(16));

            while (hashedPassword.length() < 32) {
                hashedPassword.insert(0, "0");
            }
            return hashedPassword.toString();
        }

        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /** Updates the total votes for a solution document.
     *
     * @param requestModel      the use case DS request model representing the
     *                          data of the solution document to be updated.
     *
     * @return true if update was successful, false otherwise
     */
    @Override
    default boolean updateSolutionDocVote(VoteSDocDsRequestModel requestModel) {
        updateSolutionDocVoteQuery(
                requestModel.getSolutionId(),
                requestModel.getVote()
        );
        return true;
    }
}
