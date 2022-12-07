package uc.course.updatemembers.responsemodels;

import java.time.LocalDateTime;
import java.util.List;

public class UpdateCMemMessageTreeResponseModel {

    private final String messageId;
    private final UpdateCMemUserResponseModel sender;
    private final String messageBody;
    private final LocalDateTime messageSentTimestamp;
    private final List<UpdateCMemMessageTreeResponseModel> replies;

    public UpdateCMemMessageTreeResponseModel(
            String messageId,
            UpdateCMemUserResponseModel sender,
            String messageBody,
            LocalDateTime messageSentTimestamp,
            List<UpdateCMemMessageTreeResponseModel> replies) {
        this.messageId = messageId;
        this.sender = sender;
        this.messageBody = messageBody;
        this.messageSentTimestamp = messageSentTimestamp;
        this.replies = replies;
    }

    public String getMessageId() {
        return this.messageId;
    }

    public List<UpdateCMemMessageTreeResponseModel> getReplies() {
        return this.replies;
    }

    public UpdateCMemUserResponseModel getSender() {
        return this.sender;
    }

    public String getMessageBody() {
        return this.messageBody;
    }

    public LocalDateTime getMessageSentTimestamp() {
        return this.messageSentTimestamp;
    }

}
