package usecases.doc.submitsolution.responsemodels;

import java.time.LocalDateTime;
import java.util.List;

/** SubmitSDocMessageTreeResponseModel is a bundle of data that can be used by a presenter
 * @layer use cases
 */
public class SubmitSDocMessageTreeResponseModel {

    private final String messageId;
    private final SubmitSDocUserResponseModel sender;
    private final String messageBody;
    private final LocalDateTime messageSentTimestamp;
    private final List<SubmitSDocMessageTreeResponseModel> replies;

    /** Create an instance of SubmitSDocMessageTreeResponseModel that contains information regarding the
     * message being sent, the sender of the message and replies to the message
     * 
     * @param messageId                     Id of the message sent
     * @param sender                        Sender of the message
     * @param messageBody                   Body of the message
     * @param messageSentTimestamp          Timestamp when the message was sent
     * @param replies                       List of replies to the message
     */
    public SubmitSDocMessageTreeResponseModel(
            String messageId,
            SubmitSDocUserResponseModel sender,
            String messageBody,
            LocalDateTime messageSentTimestamp,
            List<SubmitSDocMessageTreeResponseModel> replies) {
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
    public List<SubmitSDocMessageTreeResponseModel> getReplies() {
        return this.replies;
    }

    /** Gets the sender of the message
     *
     * @return user response model that contains information about the sender
     */
    public SubmitSDocUserResponseModel getSender() {
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
