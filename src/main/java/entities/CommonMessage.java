package entities;


import java.time.LocalDateTime;
/** Common Message used when a user wants to create a simple reply to a solution or comment.
 * @layer entities
 */
public class CommonMessage implements Message {

    private final String messageId;

    private final String solutionId;

    private final String userId;

    private final String parentId;
    private final String body;

    private  final LocalDateTime date;



    /** Creates a Message that contains the messageId, solutionId, parentId, body, timestamp
     *
     * @param messageId the unique id corresponding the Message.
     * @param solutionId the unique id corresponding to the Solution document the Message is part of.
     * @param userId the unique id corresponding to the creator of the Message.
     * @param parentId the id of the current message is replying to. If not Messageid then it is a rootMessageId.
     * @param body Contents of the Message.
     * @param date The Date the message was created.
     */

    public CommonMessage(String messageId, String solutionId, String userId, String parentId, String body, LocalDateTime date) {
        this.messageId = messageId;
        this.solutionId = solutionId;
        this.userId = userId;
        this.parentId = parentId;
        this.body = body;
        this.date = date;
    }

    /** Gets the MessageId of the Message
     *
     * @return return the int corresponding to the Message's Id.
     */
    @Override
    public String getMessageId() {
        return messageId;
    }

    /** Get the corresponding SolutionId the Message is part of.
     *
     * @return return the int corresponding to the SolutionId the Message is part of.
     */
    @Override
    public String getSolutionId() {
        return solutionId;
    }

    /** Get the corresponding UserId of the person who created the Message.
     *
     * @return return the int corresponding to the User the Message is part of.
     */
    @Override
    public String getUserId() {
        return userId;
    }

    /** Get the corresponding ParentId of Message or rootMessageId the Message is replying to.
     *
     * @return return the int corresponding to the ParentId or rootMessageId the Message is replying to.
     */
    @Override
    public String getParentId() {
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

    @Override
    public LocalDateTime getDate(){ return date;}
}

