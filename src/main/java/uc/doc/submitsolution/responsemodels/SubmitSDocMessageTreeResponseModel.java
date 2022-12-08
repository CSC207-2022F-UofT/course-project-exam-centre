package uc.doc.submitsolution.responsemodels;

import java.time.LocalDateTime;
import java.util.List;

public class SubmitSDocMessageTreeResponseModel {

    private final String messageId;
    private final SubmitSDocUserResponseModel sender;
    private final String messageBody;
    private final LocalDateTime messageSentTimestamp;
    private final List<SubmitSDocMessageTreeResponseModel> replies;

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

    public String getMessageId() {
        return this.messageId;
    }

    public List<SubmitSDocMessageTreeResponseModel> getReplies() {
        return this.replies;
    }

    public SubmitSDocUserResponseModel getSender() {
        return this.sender;
    }

    public String getMessageBody() {
        return this.messageBody;
    }

    public LocalDateTime getMessageSentTimestamp() {
        return this.messageSentTimestamp;
    }

}
