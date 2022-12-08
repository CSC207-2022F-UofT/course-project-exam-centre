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

    public SubmitSDocResponseModel(
            SubmitSDocSolutionDocResponseModel solutionDocModel,
            String parentTestId,
            String parentCourseId
    ) {
        this.solutionDocModel = solutionDocModel;
        this.parentTestId = parentTestId;
        this.parentCourseId = parentCourseId;
    }

    public SubmitSDocSolutionDocResponseModel getSolutionDocModel() {
        return this.solutionDocModel;
    }

    public String getParentTestId() {
        return this.parentTestId;
    }

    public String getParentCourseId() {
        return this.parentCourseId;
    }

}
