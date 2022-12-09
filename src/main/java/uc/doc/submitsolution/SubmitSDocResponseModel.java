package uc.doc.submitsolution;

import uc.doc.submitsolution.responsemodels.SubmitSDocSolutionDocResponseModel;

/**
 * SubmitSDocResponseModel bundles the data being passed back to presenter in a single object
 * @layer Use cases
 */
public class SubmitSDocResponseModel {

    private final SubmitSDocSolutionDocResponseModel solutionDocModel;
    private final String parentTestId;
    private final String parentCourseId;

    /** Create an instance of SubmitSDocResponseModel that contains the solutionDocModel, the Id of the test
     * and the Id of the course which the solution corresponds to
     * 
     * @param solutionDocModel      response model containing information of solution doc submitted
     * @param parentTestId          Id of the test which the solution corresponds to
     * @param parentCourseId        Id of the course which the solution corresponds to
     */
    public SubmitSDocResponseModel(
            SubmitSDocSolutionDocResponseModel solutionDocModel,
            String parentTestId,
            String parentCourseId
    ) {
        this.solutionDocModel = solutionDocModel;
        this.parentTestId = parentTestId;
        this.parentCourseId = parentCourseId;
    }

    /**
     * Gets the response model containing information of the solution document submitted
     * @return solution document response model
     */
    public SubmitSDocSolutionDocResponseModel getSolutionDocModel() {
        return this.solutionDocModel;
    }

    /**
     * Gets the Id of the test corresponding to this solution document
     * @return the parent test Id
     */
    public String getParentTestId() {
        return this.parentTestId;
    }

    /**
     * Gets the Id of the course corresponding to this solution document
     * @return the parent course Id
     */
    public String getParentCourseId() {
        return this.parentCourseId;
    }

}
