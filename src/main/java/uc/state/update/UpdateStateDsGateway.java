package uc.state.update;

import java.util.List;

public interface UpdateStateDsGateway {
    List<String> getAllCourseIds();
    List<List<String>> getAllTestDocsByCourseId(String courseId);
    List<List<String>> getAllSolutionDocsByTestId(String testId);
    List<List<String>> getMessagesByParentId(String parentId);
    List<List<String>> getEnrolmentsByUserId(String userId);
    List<String> getUserById(String userId);
    List<String> getCourseById(String courseId);
}
