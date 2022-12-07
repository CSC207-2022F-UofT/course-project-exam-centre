package uc.state.update;

import uc.state.update.dbmodels.*;

import java.util.List;

public interface UpdateStateDsGateway {
    List<? extends UpdateStateTestDocDbModel> getTestDocsByCourseId(String courseId);
    List<? extends UpdateStateSolutionDocDbModel> getSolutionDocsByTestId(String testId);

    List<? extends UpdateStateMessageDbModel> getMessagesByParentId(String parentId);
    UpdateStateUserDbModel getUserById(String userId);
    UpdateStateCourseDbModel getCourseById(String courseId);
    List<String> getAllCourseIds();
    List<String> getCourseIdsByUserId(String userId);

    boolean getConnectionStatus();
}
