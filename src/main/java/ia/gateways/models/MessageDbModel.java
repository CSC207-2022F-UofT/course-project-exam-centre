package ia.gateways.models;

import uc.state.update.UpdateStateMessageDbModel;

import java.time.LocalDateTime;

public class MessageDbModel implements UpdateStateMessageDbModel {

    private final String messageId;
    private final String solutionId;
    private final String userId;
    private final String parentId;
    private final String messageBody;
    private final LocalDateTime messageSentTimestamp;

    public MessageDbModel(String messageId,
                          String solutionId,
                          String userId,
                          String parentId,
                          String messageBody,
                          LocalDateTime messageSentTimestamp) {
        this.messageId = messageId;
        this.solutionId = solutionId;
        this.userId = userId;
        this.parentId = parentId;
        this.messageBody = messageBody;
        this.messageSentTimestamp = messageSentTimestamp;
    }

    public String getMessageId() {
        return this.messageId;
    }

    public String getSolutionId() {
        return this.solutionId;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getParentId() {
        return this.parentId;
    }

    public String getMessageBody() {
        return this.messageBody;
    }

    public LocalDateTime getMessageSentTimestamp() {
        return this.messageSentTimestamp;
    }

}
