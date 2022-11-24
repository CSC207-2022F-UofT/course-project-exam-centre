package uc.dboard.submessage;

// Use case layer

import java.time.LocalDateTime;

public class SubDBMessDsRequestModel {

    private final int solutionId;

    private final int userId;

    private final int parentId;

    private final String body;

    private final LocalDateTime creationTime;

    /** Creates a SubDBMessDsRequest Model that contains the solutionId, userId, parentId, body and timestamp.
     *
     * @param solutionId the unique id corresponding to the Solution document the Message is part of.
     * @param userId the unique id corresponding to the creator of the Message.
     * @param parentId the id of the current message is replying to. If not Messageid then it is a rootMessageId.
     * @param body Contents of the Message.
     * @param creationTime Time the message was created.
     */

    public SubDBMessDsRequestModel(int solutionId, int userId, int parentId, String body, LocalDateTime creationTime) {
        this.solutionId = solutionId;
        this.userId = userId;
        this.parentId = parentId;
        this.body = body;
        this.creationTime = creationTime;
    }

    /** Gets the SolutionID of the Message
     *
     * @return return the int corresponding to the Message's Solution Id.
     */
    public int getSolutionId() {
        return solutionId;
    }

    /** Gets the UserID of the Message
     *
     * @return return the int corresponding to the Message's User Id.
     */
    public int getUserId() {
        return userId;
    }

    /** Gets the ParentId of the Message
     *
     * @return return the int corresponding to the Message's Parent Id.
     */
    public int getParentId() {
        return parentId;
    }

    /** Gets the Contents of the message body of the Message
     *
     * @return return the String corresponding to the Message's Body.
     */
    public String getBody() {
        return body;
    }

    /** Gets the creation time of the Message
     *
     * @return return the LocalDateTime corresponding to the time the message was created.
     */
    public LocalDateTime getCreationTime() {
        return creationTime;
    }
}
