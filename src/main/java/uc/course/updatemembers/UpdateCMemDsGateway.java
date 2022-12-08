package uc.course.updatemembers;

import uc.course.updatemembers.dbmodels.*;

import java.util.List;

/** UpdateCMemDsGateway provides methods to compare, access or update persistent data
 * @layer use cases
 */
public interface UpdateCMemDsGateway
{
    /**
     * Checks if the courseId exists within persistent data
     * @param courseId A courseId of a course
     * @return if the courseId exists within persistent data
     */
    boolean checkIfCourseExists(String courseId);

    /**
     * Updates the user's enrollment into a course in persistent data
     * @param courseId the courseId of the course being enrolled into
     * @param userId the userId of the user enrolling into a course
     */
    void addCourseEnrolment(String courseId, String userId);

    /**
     * Updates the user's enrollment status in a course in persistent data
     * @param courseId the courseId of the course being dropped
     * @param userId the userId of the user dropping the course
     */
    void removeCourseEnrolment(String courseId, String userId);

    /**
     * Gets the CourseIds that a user is enrolled in
     * @param userId the userId of the user being checked
     * @return a list of the CourseIds the user is enrolled in
     */
    List<String> getCourseIdsByUserId(String userId);

    /**
     * Gets a User DB model representing a user by ID
     * @param userId the userId of the user being retrieved
     * @return UpdateCMemUserDbModel representing a user
     */
    UpdateCMemUserDbModel getUserById(String userId);

    // TODO: Update JavaDoc
    boolean getConnectionStatus();
    List<? extends UpdateCMemTestDocDbModel> getTestDocsByCourseId(String courseId);
    List<? extends UpdateCMemSolutionDocDbModel> getSolutionDocsByTestId(String testId);

    List<? extends UpdateCMemMessageDbModel> getMessagesByParentId(String parentId);
    UpdateCMemCourseDbModel getCourseById(String courseId);
}
