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

    /** Create an instance of SubmitTDocResponseModel that contains the test doc response model and 
     * Id of the course the test is being added to
     * 
     * @param testDocModel          response model containing information regarding the test document
     * @param parentCourseId        Id of the course the test is being added to
     */
    public SubmitTDocResponseModel(
            SubmitTDocTestDocResponseModel testDocModel,
            String parentCourseId
    ) {
        this.testDocModel = testDocModel;
        this.parentCourseId = parentCourseId;
    }

    /** Gets the test doc model
     *
     * @return the test doc model
     */
    public SubmitTDocTestDocResponseModel getTestDocModel() {
        return this.testDocModel;
    }

    /** Gets the Id of the course the test is being added to
     *
     * @return the Id of the course
     */
    public String getParentCourseId() {
        return this.parentCourseId;
    }
}
