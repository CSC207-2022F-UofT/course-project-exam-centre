package usecases.doc.submitsolution.responsemodels;

/** SubmitSDocSolutionDocResponseModel is a bundle of data that can be used by a presenter
 * @layer use cases
 */
public class SubmitSDocSolutionDocResponseModel {

    private final String solutionId;
    private final Integer voteTotal;
    private final Float recordedScore;
    private final Float estimatedTime;
    private final String solutionName;
    private final SubmitSDocMessageTreeResponseModel rootMessage;

    /** Create an instance of SubmitSDocSolutionDocResponseModel that contains information regarding the
     * solution document
     * 
     * @param solutionId            Id of the solution
     * @param voteTotal             Total number of votes for the solution
     * @param recordedScore         Score recorded for the solution
     * @param estimatedTime         Estimated amount of time to complete the solution
     * @param solutionName          Name of the solution
     * @param rootMessage           Root message in the solution
     */
    public SubmitSDocSolutionDocResponseModel(
            String solutionId,
            Integer voteTotal,
            Float recordedScore,
            Float estimatedTime,
            String solutionName,
            SubmitSDocMessageTreeResponseModel rootMessage) {

        this.solutionId = solutionId;
        this.voteTotal = voteTotal;
        this.recordedScore = recordedScore;
        this.estimatedTime = estimatedTime;
        this.solutionName = solutionName;
        this.rootMessage = rootMessage;
    }

    /** Gets the Id of the solution submitted
     *
     * @return the Id of the solution 
     */
    public String getSolutionId() {
        return this.solutionId;
    }

    /** Gets the total number of votes on the solution
     *
     * @return the total number of votes
     */
    public Integer getVoteTotal() {
        return this.voteTotal;
    }

    /** Gets the recorded score of the solution
     *
     * @return the recorded score
     */
    public Float getRecordedScore() {
        return this.recordedScore;
    }

    /** Gets the estimated amount of time taken to complete the solution
     *
     * @return the estimated amount of time
     */
    public Float getEstimatedTime() {
        return this.estimatedTime;
    }

    /** Gets the root message in the solution's discussion board
     *
     * @return the message tree response model containing the root message details
     */
    public SubmitSDocMessageTreeResponseModel getRootMessage() {
        return this.rootMessage;
    }

    /** Gets the name of the solution submitted
     *
     * @return the name of the solution 
     */
    public String getSolutionName() {
        return this.solutionName;
    }

}
