package uc.doc.submittest;

import uc.doc.submittest.responsemodels.SubmitTDocTestDocResponseModel;

/**
 * SubmitTDocResponseModel bundles the information of the saved test document
 * to be passed back to the presenters
 * @layer Use cases
 */
public class SubmitTDocResponseModel {

    private final SubmitTDocTestDocResponseModel testDocModel;
    private final String parentCourseId;

    public SubmitTDocResponseModel(
            SubmitTDocTestDocResponseModel testDocModel,
            String parentCourseId
    ) {
        this.testDocModel = testDocModel;
        this.parentCourseId = parentCourseId;
    }

    public SubmitTDocTestDocResponseModel getTestDocModel() {
        return this.testDocModel;
    }

    public String getParentCourseId() {
        return this.parentCourseId;
    }
}
