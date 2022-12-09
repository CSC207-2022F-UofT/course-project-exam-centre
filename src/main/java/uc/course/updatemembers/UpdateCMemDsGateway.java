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

    /** Checks whether gateway is connected to database.
     *
     * @return boolean representing whether database is connected
     */
    boolean getConnectionStatus();

    /** Gets a list containing model representations of
     * each test document entity that shares the given course ID.
     *
     * @param courseId the course ID shared by the requested test document entities
     * @return list containing TestDocDbModel objects which each represent the data
     * for a test document entity
     */
    List<? extends UpdateCMemTestDocDbModel> getTestDocsByCourseId(String courseId);
    
    /** Gets a list containing model representations of
     * each solution document entity that shares the given test ID.
     *
     * @param testId the test ID shared by the requested solution document entities
     * @return list containing SolutionDocDbModel objects which each represent the data
     * for a solution document entity
     */
    List<? extends UpdateCMemSolutionDocDbModel> getSolutionDocsByTestId(String testId);

    /** Gets a list containing model representations of
     * each message entity that shares the given parent ID.
     *
     * @param parentId the parent message ID shared by the messages being requested
     * @return list containing MessageDbModel objects which each represent the data
     * for a message entity
     */
    List<? extends UpdateCMemMessageDbModel> getMessagesByParentId(String parentId);
    
    /** Gets course data by course ID.
     *
     * @param courseId the unique ID of the course being requested
     *
     * @return CourseDbModel object representing the data for the
     * requested course entity
     */
    UpdateCMemCourseDbModel getCourseById(String courseId);
}
