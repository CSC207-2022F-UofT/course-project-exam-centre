package uc.dboard.submessage;

/** SubDBMessRequestModel is responsible for packaging data for SubmitDBMessageInteractor
 * @layer use cases
 */
public class SubDBMessRequestModel {


    private final String solutionId;

    private final String userId;

    private final String parentId;

    private final String body;

    /** Creates a SubDBMessRequest Model that contains the solutionId, userId, parentId, body and timestamp.
     *
     * @param solutionId        the unique id corresponding to the Solution document the Message is part of.
     * @param userId            the unique id corresponding to the creator of the Message.
     * @param parentId          the id of the current message is replying to. If not Messageid then it is a rootMessageId.
     * @param body              Contents of the Message.
     */
    public SubDBMessRequestModel(
            String solutionId,
            String userId,
            String parentId,
            String body){

        this.solutionId = solutionId;
        this.userId = userId;
        this.parentId = parentId;
        this.body = body;
    }

    /** Gets the solutionId corresponding to the message
     *
     * @return a string containing the solutionId
     */
    public String getSolutionId() {
        return solutionId;
    }

    /** Gets the userId corresponding to the message
     *
     * @return a string containing the solutionId
     */
    public String getUserId() {
        return userId;
    }

    /** Gets the messageId of the parent message. If the message is a parent,
     * it will get the rootMessageId.
     *
     * @return a string containing the rootMessageId or messageId
     */
    public String getParentId() {
        return parentId;
    }

    /** Gets the body corresponding to the message
     *
     * @return the text body of the message
     */
    public String getBody() {
        return body;
    }
}
