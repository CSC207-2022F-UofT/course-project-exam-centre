package uc.dboard.submessage;

import uc.dboard.submessage.responsemodels.*;

/** SubDBMessResponseModel is responsible for packaging data in a way a presenter can use
 * @layer use cases
 */
public class SubDBMessResponseModel {

    private final SubDBMessMessageTreeResponseModel newMessageTree;
    private final String solutionId;
    private final String parentTestId;
    private final String parentCourseId;

    /** Constructs a SubDBMessResponseModel
     *
     * @param newMessageTree    a new message tree model
     * @param solutionId        the solution for the message tree
     * @param parentTestId      the parent test ID
     * @param parentCourseId    the parent course ID
     */
    public SubDBMessResponseModel(
            SubDBMessMessageTreeResponseModel newMessageTree,
            String solutionId,
            String parentTestId,
            String parentCourseId) {
        this.newMessageTree = newMessageTree;
        this.solutionId = solutionId;
        this.parentTestId = parentTestId;
        this.parentCourseId = parentCourseId;
    }

    /** Gets the message tree response model associated with the message
     *
     * @return message tree response model
     */
    public SubDBMessMessageTreeResponseModel getMessageTree() {
        return this.newMessageTree;
    }

    /** Gets the solutionId corresponding to the message
     *
     * @return a string containing the solutionId
     */
    public String getSolutionId() {
        return this.solutionId;
    }

    /** Gets the test Id corresponding to the solution the message belongs to
     *
     * @return a string containing the testId
     */
    public String getParentTestId() {
        return this.parentTestId;
    }

    /** Gets the course Id corresponding to the test and solution which the message belongs to
     *
     * @return a string containing the courseId
     */
    public String getParentCourseId() {
        return this.parentCourseId;
    }


}
