package entities;

// Entity layer

<<<<<<< HEAD
=======
import java.time.LocalDateTime;

>>>>>>> origin/8-use-case-8-submit-discussion-board-message
class CommonMessage implements Message {

    private final int messageId;

    private final int solutionId;

    private final int userId;

    private final int parentId;
    private final String body;

<<<<<<< HEAD


=======
>>>>>>> origin/8-use-case-8-submit-discussion-board-message
    /** Creates a Message that contains the messageId, solutionId, parentId, body, timestamp
     *
     * @param messageId the unique id corresponding the Message.
     * @param solutionId the unique id corresponding to the Solution document the Message is part of.
     * @param userId the unique id corresponding to the creator of the Message.
     * @param parentId the id of the current message is replying to. If not Messageid then it is a rootMessageId.
     * @param body Contents of the Message.
     */

    CommonMessage(int messageId, int solutionId, int userId, int parentId, String body) {
        this.messageId = messageId;
        this.solutionId = solutionId;
        this.userId = userId;
        this.parentId = parentId;
        this.body = body;
    }

    /** Gets the MessageId of the Message
     *
     * @return return the int corresponding to the Message's Id.
     */
    @Override
    public int getMessageId() {
        return messageId;
    }

    /** Get the corresponding SolutionId the Message is part of.
     *
     * @return return the int corresponding to the SolutionId the Message is part of.
     */
    @Override
    public int getSolutionId() {
        return solutionId;
    }

    /** Get the corresponding UserId of the person who created the Message.
     *
     * @return return the int corresponding to the User the Message is part of.
     */
    @Override
    public int getUserId() {
        return userId;
    }

    /** Get the corresponding ParentId of Message or rootMessageId the Message is replying to.
     *
     * @return return the int corresponding to the ParentId or rootMessageId the Message is replying to.
     */
    @Override
    public int getParentId() {
        return parentId;
    }

    /** Get the contents of the Message
     *
     * @return return the String corresponding to the contents of the Message.
     */
    @Override
    public String getBody() {
        return body;
    }
}

