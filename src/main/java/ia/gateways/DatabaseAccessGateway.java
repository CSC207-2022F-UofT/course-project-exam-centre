package ia.gateways;

import ia.gateways.models.*;
import uc.state.update.UpdateStateDsGateway;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface DatabaseAccessGateway
        extends UpdateStateDsGateway {

    // Raw data requests to be implemented
    List<String> getAllCourseIdsRawData();
    List<String> getCourseByIdRawData(String courseId);
    List<String> getUserByIdRawData(String userId);
    List<String> getCoursesByUserIdRawData(String userId);
    List<List<String>> getMessagesByParentIdRawData(String parentId);
    List<List<String>> getTestDocsByCourseIdRawData(String courseId);
    List<List<String>> getSolutionDocsByTestIdRawData(String testId);

    default List<String> getAllCourseIds() {
        return getAllCourseIdsRawData();
    }

    default CourseDbModel getCourseById(String inputId) {
        List<String> rawCourseData = getCourseByIdRawData(inputId);

        return new CourseDbModel(
                rawCourseData.get(0),           // courseId
                rawCourseData.get(1),           // courseCode
                rawCourseData.get(2)            // courseName
        );
    }

    default UserDbModel getUserById(String inputId) {
        List<String> rawUserData = getUserByIdRawData(inputId);

        return new UserDbModel(
                rawUserData.get(0),         // userId
                rawUserData.get(1),         // email
                rawUserData.get(2),         // firstName
                rawUserData.get(3)          // lastName
        );
    }

    default List<String> getCourseIdsByUserId(String inputId) {
        return getCoursesByUserIdRawData(inputId);
    }

    default List<MessageDbModel> getMessagesByParentId(String inputId) {
        List<List<String>> rawMessagesData = getMessagesByParentIdRawData(inputId);
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
        List<List<String>> rawTestDocData = getTestDocsByCourseIdRawData(inputId);
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
        List<List<String>> rawSolutionDocData = getSolutionDocsByTestIdRawData(inputId);
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

    boolean getConnectionStatus();

}
