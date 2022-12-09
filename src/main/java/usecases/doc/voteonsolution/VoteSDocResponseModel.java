package usecases.doc.voteonsolution;

/** VoteSDocResponseModel is a bundle of data that can be used by a presenter
 * @layer use cases
 */
public class VoteSDocResponseModel {
    private final String courseId;
    private final String testId;
    private final String solutionId;
    private final int voteTotal;

    /** Create an instance of VoteSDocResponseModel that contains the total number of votes, the solutionDoc
     * and the timestamp of the request
     *
     * @param courseId Id of the course which the solution belongs to
     * @param testId Id of the test the solution belongs to
     * @param solutionId Id of the solution being voted on
     * @param voteTotal total number of votes on the sDoc
     */
    public VoteSDocResponseModel(String courseId, String testId, String solutionId, int voteTotal) {
        this.courseId = courseId;
        this.testId = testId;
        this.solutionId = solutionId;
        this.voteTotal = voteTotal;
    }
    
    /** Gets the Id of the course which the solution belongs to
     *
     * @return the Id of the course
     */
    public String getCourseId() {
        return this.courseId;
    }

    /** Gets the Id of the test which the solution belongs to
     *
     * @return the Id of the test
     */
    public String getTestId() {
        return this.testId;
    }

    /** Gets the total number of votes that a solution has
     *
     * @return the total votes of the solution
     */
    public int getVoteTotal() {
        return this.voteTotal;
    }

    /** Gets the solutionId of the solution that is being updated
     *
     * @return the solutionId of the solution that is being voted on
     */
    public String getSolutionId() {
        return this.solutionId;
    }

}
