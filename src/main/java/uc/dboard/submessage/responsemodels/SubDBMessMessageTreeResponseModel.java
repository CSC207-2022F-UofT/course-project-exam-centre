package uc.dboard.submessage.responsemodels;

import java.time.LocalDateTime;
import java.util.List;

public class SubDBMessMessageTreeResponseModel {

    private final String messageId;
    private final SubDBMessUserResponseModel sender;
    private final String messageBody;
    private final LocalDateTime messageSentTimestamp;
    private final List<SubDBMessMessageTreeResponseModel> replies;

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

    public String getMessageId() {
        return this.messageId;
    }

    public List<SubDBMessMessageTreeResponseModel> getReplies() {
        return this.replies;
    }

    public SubDBMessUserResponseModel getSender() {
        return this.sender;
    }

    public String getMessageBody() {
        return this.messageBody;
    }

    public LocalDateTime getMessageSentTimestamp() {
        return this.messageSentTimestamp;
    }

}
