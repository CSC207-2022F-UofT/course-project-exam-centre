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
     * @param newMessageTree a new message tree model
     * @param solutionId the solution for the message tree
     * @param parentTestId the parent test ID
     * @param parentCourseId the parent course ID
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

    public SubDBMessMessageTreeResponseModel getMessageTree() {
        return this.newMessageTree;
    }

    public String getSolutionId() {
        return this.solutionId;
    }

    public String getParentTestId() {
        return this.parentTestId;
    }

    public String getParentCourseId() {
        return this.parentCourseId;
    }


}
