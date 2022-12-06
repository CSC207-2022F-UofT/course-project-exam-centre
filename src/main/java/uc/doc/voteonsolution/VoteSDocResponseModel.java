package uc.doc.voteonsolution;

import java.time.LocalDateTime;

import entities.SolutionDocument;

/** VoteSDocResponseModel is a bundle of data that can be used by a presenter
 * @layer use cases
 */
public class VoteSDocResponseModel {
    private final int voteTotal;
    private final SolutionDocument solutionDoc;
    private final LocalDateTime timestamp;

    /** Create an instance of VoteSDocResponseModel that contains the total number of votes, the solutionDoc
     * and the timestamp of the request
     *
     * @param voteTotal total number of votes on the sDoc
     * @param solutionDoc the solutionDoc being updated
     * @param timestamp the timestamp of the vote solution request
     */
    public VoteSDocResponseModel(int voteTotal, SolutionDocument solutionDoc, LocalDateTime timestamp) {
        this.voteTotal = voteTotal;
        this.solutionDoc = solutionDoc;
        this.timestamp = timestamp;
    }

    /** Gets the total number of votes that a solution has
     *
     * @return the total votes of the solution
     */
    public int getVoteTotal() {
        return this.voteTotal;
    }

    /** Gets the sDoc that is being updated
     *
     * @return the sDoc entity that is being voted on
     */
    public SolutionDocument getSolutionDoc() {
        return this.solutionDoc;
    }

    /** Get the timestamp of the request
     *
     * @return a time corresponding to the timestamp of the request
     */
    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }
}
