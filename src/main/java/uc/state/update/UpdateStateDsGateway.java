package uc.state.update;

import java.util.List;

/** UpdateStateDsGateway provides methods to access and update persistent data
 * @layer use cases
 */
public interface UpdateStateDsGateway {
    /**
     * Gets the test documents by their courseId from persistent data
     * @param courseId the courseId of the course
     * @return a list of TestDocDbModels that contain information about the test docs belonging to a course
     */
    List<? extends UpdateStateTestDocDbModel> getTestDocsByCourseId(String courseId);

    /**
     * Gets the solution docs in persistent data by their testId
     * @param testId the testId of the test of interest
     * @return a list of SolutionDocDBModels that contain information about the solution docs belonging to a test
     */
    List<? extends UpdateStateSolutionDocDbModel> getSolutionDocsByTestId(String testId);

    /** TODO: finish
     *
     * @param parentId
     * @return
     */
    List<? extends UpdateStateMessageDbModel> getMessagesByParentId(String parentId);

    /** TODO: finish
     *
     * @param userId
     * @return
     */
    UpdateStateUserDbModel getUserById(String userId);

    /** TODO: finish
     *
     * @param courseId
     * @return
     */
    UpdateStateCourseDbModel getCourseById(String courseId);

    /** TODO: finish
     *
     * @return a list of all courseIds
     */
    List<String> getAllCourseIds();

    /** TODO: finish
     *
     * @param userId
     * @return a list of all courseIds corresponding to a userId
     */
    List<String> getCourseIdsByUserId(String userId);
}
