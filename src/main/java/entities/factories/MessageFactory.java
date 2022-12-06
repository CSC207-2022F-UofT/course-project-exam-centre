package entities.factories;

// Entity layer

import entities.CommonMessage;
import entities.Message;

import java.time.LocalDateTime;

public class MessageFactory {

    /**
     * Returns a new Message object.
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
