package ia.gateways;

import ia.gateways.models.*;
import uc.course.register.CRegisterDsGateway;
import uc.course.register.CRegisterDsRequestModel;
import uc.course.updatemembers.UpdateCMemDsGateway;
import uc.dboard.submessage.SubDBMessDsGateway;
import uc.dboard.submessage.SubDBMessDsRequestModel;
import uc.state.update.UpdateStateDsGateway;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface DatabaseAccessGateway
        extends UpdateStateDsGateway,
        CRegisterDsGateway,
        UpdateCMemDsGateway,
        SubDBMessDsGateway {

    // Query methods to be implemented

    boolean checkIfCourseExistsQuery(String courseId);
    boolean checkIfUserExistsQuery(String userId);
    boolean checkIfUserExistsByEmailQuery(String email);

    List<String> getAllCourseIdsQuery();
    List<String> getCourseByIdQuery(String courseId);
    List<String> getUserByIdQuery(String userId);
    List<String> getCourseIdsByUserIdQuery(String userId);

    List<List<String>> getMessagesByParentIdQuery(String parentId);
    List<List<String>> getTestDocsByCourseIdQuery(String courseId);
    List<List<String>> getSolutionDocsByTestIdQuery(String testId);

    String getTestIdBySolutionIdQuery(String solutionId);
    String getCourseIdByTestIdQuery(String solutionId);

    void saveCourseQuery(String courseId,
                         String courseCode,
                         String courseName);

    void addMessageQuery(String messageId,
                         String solutionId,
                         String userId,
                         String parentId,
                         String messageBody);

    void addCourseEnrolmentQuery(
            String enrolmentId,
            String courseId,
            String userId);

    void removeCourseEnrolmentQuery(
            String courseId,
            String userId);

//    void saveSolutionDocumentQuery(
//            String solutionId,
//            String testId,
//            String userId,
//            String voteTotal,
//            String recordedScore,
//            String estimatedTime,
//            String rootMessageId,
//            String solutionName
//    );


    // Default Methods

    default void addCourseEnrolment(
            String courseId,
            String userId) {

        String enrolmentId = generateRandomId();
        addCourseEnrolmentQuery(
                enrolmentId,
                courseId,
                userId);
    }

    default void removeCourseEnrolment(
            String courseId,
            String userId) {

        removeCourseEnrolmentQuery(
                courseId,
                userId);
    }

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

    default String getTestIdBySolutionId(String solutionId) {
        return getTestIdBySolutionIdQuery(solutionId);
    }

    default String getCourseIdByTestId(String testId) {
        return getCourseIdByTestIdQuery(testId);
    }

    default boolean checkIfCourseExists(String courseId) {
        return checkIfCourseExistsQuery(courseId);
    }

    default boolean checkIfUserExists(String courseId) {
        return checkIfUserExistsQuery(courseId);
    }

    default boolean checkIfUserExistsByEmail(String email) {
        return checkIfUserExistsByEmailQuery(email);
    }

    default String saveCourse(CRegisterDsRequestModel requestModel) {
        String courseId = generateRandomId();
        saveCourseQuery(
                courseId,
                requestModel.getCourseCode(),
                requestModel.getCourseName()
                );
        return courseId;
    }

    default List<String> getAllCourseIds() {
        return getAllCourseIdsQuery();
    }

    default CourseDbModel getCourseById(String inputId) {
        List<String> rawCourseData = getCourseByIdQuery(inputId);

        return new CourseDbModel(
                rawCourseData.get(0),           // courseId
                rawCourseData.get(1),           // courseCode
                rawCourseData.get(2)            // courseName
        );
    }

    default UserDbModel getUserById(String inputId) {
        List<String> rawUserData = getUserByIdQuery(inputId);

        return new UserDbModel(
                rawUserData.get(0),         // userId
                rawUserData.get(1),         // email
                rawUserData.get(2),         // firstName
                rawUserData.get(3)          // lastName
        );
    }

    default List<String> getCourseIdsByUserId(String inputId) {
        return getCourseIdsByUserIdQuery(inputId);
    }

    default List<MessageDbModel> getMessagesByParentId(String inputId) {
        List<List<String>> rawMessagesData = getMessagesByParentIdQuery(inputId);
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

    default List<TestDocDbModel> getTestDocsByCourseId(String inputId) {
        List<List<String>> rawTestDocData = getTestDocsByCourseIdQuery(inputId);
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

    default List<SolutionDocDbModel> getSolutionDocsByTestId(String inputId) {
        List<List<String>> rawSolutionDocData = getSolutionDocsByTestIdQuery(inputId);
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

    // Generate random alphanumeric ID
    default String generateRandomId(){
        UUID randomUUID = UUID.randomUUID();
        return randomUUID.toString()
                .substring(0,8)
                .replaceAll("-", "");
    }

    default String hashPassword(String unhashedPassword)
    {
        try {

            MessageDigest messDigest = MessageDigest.getInstance("SHA-512");

            byte[] messDigestBytes = messDigest.digest(unhashedPassword.getBytes());
            BigInteger signumRepresentation = new BigInteger(1, messDigestBytes);
            StringBuilder hashedPassword = new StringBuilder(signumRepresentation.toString(16));

            while (hashedPassword.length() < 32) {
                hashedPassword.insert(0, "0");
            }
            return hashedPassword.toString();
        }

        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    boolean getConnectionStatus();

}
