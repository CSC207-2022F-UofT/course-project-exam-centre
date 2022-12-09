package usecases.submessage.responsemodels;

import java.time.LocalDateTime;
import java.util.List;

/** SubDBMessMessageTreeResponseModel is a bundle of data that can be used by a presenter
 * @layer use cases
 */
public class SubDBMessMessageTreeResponseModel {

    private final String messageId;
    private final SubDBMessUserResponseModel sender;
    private final String messageBody;
    private final LocalDateTime messageSentTimestamp;
    private final List<SubDBMessMessageTreeResponseModel> replies;

    /** Create an instance of SubDBMessMessageTreeResponseModel that contains the information of the message
     * in the tree, the sender and the replies to the message
     * 
     * @param messageId                 Id of the message in the tree
     * @param sender                    user response model containing information on the sender of the message
     * @param messageBody               body of the message sent
     * @param messageSentTimestamp      timestamp of the message sent
     * @param replies                   replies to the message sent
     */
    public SubDBMessMessageTreeResponseModel(
            String messageId,
            SubDBMessUserResponseModel sender,
            String messageBody,
            LocalDateTime messageSentTimestamp,
            List<SubDBMessMessageTreeResponseModel> replies) {
        this.messageId = messageId;
        this.sender = sender;
        this.messageBody = messageBody;
        this.messageSentTimestamp = messageSentTimestamp;
        this.replies = replies;
    }

    /** Gets the Id of the user which sent the message
     *
     * @return the Id of the user
     */
    public String getMessageId() {
        return this.messageId;
    }

    /** Gets the replies to the message sent
     *
     * @return list of message tree response models that represent replies
     */
    public List<SubDBMessMessageTreeResponseModel> getReplies() {
        return this.replies;
    }

    /** Gets the sender of the message
     *
     * @return user response model that contains information about the sender
     */
    public SubDBMessUserResponseModel getSender() {
        return this.sender;
    }

    /** Gets the body of the message sent
     *
     * @return the body of the message
     */
    public String getMessageBody() {
        return this.messageBody;
    }

    /** Gets the timestamp of the message sent
     *
     * @return the timestamp of the message
     */
    public LocalDateTime getMessageSentTimestamp() {
        return this.messageSentTimestamp;
    }

}
