package entities;

// Entity layer

import java.time.LocalDateTime;

public class MessageFactory {

    /**
     * Returns a new Message object.
     */
    public static Message create(
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
