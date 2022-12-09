package uc.doc.submittest.responsemodels;

import uc.state.update.responsemodels.UpdateStateSolutionDocResponseModel;

import java.util.Map;

/**
 * SubmitTDocTestDocResponseModel bundles the information of a test document
 * to be passed back to the presenters
 * @layer Use cases
 */
public class SubmitTDocTestDocResponseModel {

    private final String testId;
    private final String userId;
    private final String testType;
    private final Integer numOfQuestions;
    private final Float estimatedTime;
    private final String testName;
    private final Map<String, UpdateStateSolutionDocResponseModel> solutionModels;

    /** Create an instance of SubmitTDocTestDocResponseModel that contains all information regarding the
     * test document
     * 
     * @param testId                Id of the test being submitted
     * @param userId                Id of the user that submitted the test
     * @param testType              Type of test
     * @param numOfQuestions        Number of questions in the test
     * @param estimatedTime         Estimated amount of time required for the test
     * @param testName              Name of the test
     * @param solutionModels        Map of solution doc response models that correspond to the test
     */
    public SubmitTDocTestDocResponseModel(
            String testId,
            String userId,
            String testType,
            Integer numOfQuestions,
            Float estimatedTime,
            String testName,
            Map<String, UpdateStateSolutionDocResponseModel> solutionModels) {

        this.testId = testId;
        this.userId = userId;
        this.testType = testType;
        this.numOfQuestions = numOfQuestions;
        this.estimatedTime = estimatedTime;
        this.testName = testName;
        this.solutionModels = solutionModels;
    }

    /** Gets the solution doc response models that correspond to the test
     *
     * @return map of solution doc response models
     */
    public Map<String, UpdateStateSolutionDocResponseModel> getSolutionModels() {
        return this.solutionModels;
    }

    /** Gets the Id of the test which is being submitted
     *
     * @return the Id of the test
     */
    public String getTestId() {
        return this.testId;
    }

    /** Gets the Id of the user that submitted the test
     *
     * @return the Id of the user
     */
    public String getUserId() {
        return this.userId;
    }

    /** Gets the type of the test
     *
     * @return the test type
     */
    public String getTestType() {
        return this.testType;
    }

    /** Gets the number of questions in the test
     *
     * @return number of questions
     */
    public Integer getNumOfQuestions() {
        return this.numOfQuestions;
    }

    /** Gets the estimated amount of time required for the test
     *
     * @return the estimated time
     */
    public Float getEstimatedTime() {
        return this.estimatedTime;
    }

    /** Gets the name of the test
     *
     * @return the test name
     */
    public String getTestName() {
        return this.testName;
    }

}
