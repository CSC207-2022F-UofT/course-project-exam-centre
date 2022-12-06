package uc.state.update;

/** UpdateStateTestDocDbModel provides methods TODO: Finish
 * @layer use cases
 */
public interface UpdateStateTestDocDbModel {
    /**
     * TODO: Finish
     * @return a string representing the testId
     */
    String getTestId();

    /**
     * TODO: Finish
     * @return a string representing the userId
     */
    String getUserId();

    /**
     * TODO: Finish
     * @return a string representing the test type
     */
    String getTestType();

    /**
     * TODO: Finish
     * @return an integer that represents how many questions are on a test
     */
    Integer getNumOfQuestions();

    /**
     * TODO: Finish
     * @return a float that represents the estimated time it took to complete a test
     */
    Float getEstimatedTime();

    /**
     * TODO: Finish
     * @return a string containing the testName
     */
    String getTestName();
}
