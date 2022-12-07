package uc.dboard.submessage;

import java.time.LocalDateTime;

/** SubDBMessResponseModel is responsible for packaging data in a way a presenter can use
 * @layer use cases
 */
public class SubDBMessResponseModel {

    String messageBody;
    String creationTime;

    String parentId;

    /** Constructs a SubDBMessResponseModel containing the messageBody, creationTime, and parentId
     *
     * @param messageBody the body of the message
     * @param creationTime how long it takes to create the message
     * @param parentId either the messageId of the parent or rootMessageId
     */
    public SubDBMessResponseModel(String messageBody, String creationTime, String parentId) {
        this.messageBody = messageBody;
        this.creationTime = creationTime;
        this.parentId = parentId;
    }

    /**
     * Gets the message body of the message.
     *
     * @return the body of the message
     */
    public  String getMessageBody(){return messageBody;}

    /**
     * Gets the creation time of the message.
     *
     * @return how long it took to create the message
     */
    public  String getCreationTime(){return creationTime;}


}
