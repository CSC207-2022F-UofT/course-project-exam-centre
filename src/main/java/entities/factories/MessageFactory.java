package entities.factories;

// Entity layer

import entities.CommonMessage;
import entities.Message;

import java.time.LocalDateTime;

public class MessageFactory {

    /** Creates a new Message object
     *
     * @param messageId unique identifier of the message
     * @param solutionId unique identifier of the solution document the message is associated to.
     * @param userId unique identifier of user writing the message
     * @param parentId unique identifier of parent message
     * @param body the body of the message
     * @param sentTimestamp the time the message was sent.
     * @return a new CommonMessage Object
     */
    public Message create(
            String messageId,
            String solutionId,
            String userId,
            String parentId,
            String body,
            LocalDateTime sentTimestamp) {
        return new CommonMessage(
                messageId,
                solutionId,
                userId,
                parentId,
                body,
                sentTimestamp);
    }
}
