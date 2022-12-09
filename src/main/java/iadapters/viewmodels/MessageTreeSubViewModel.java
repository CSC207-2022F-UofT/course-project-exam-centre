package iadapters.viewmodels;

import java.time.LocalDateTime;
import java.util.List;

public class MessageTreeSubViewModel {

    private final String messageId;
    private final UserSubViewModel sender;
    private final String messageBody;
    private final LocalDateTime messageSentTimestamp;
    private final List<MessageTreeSubViewModel> replies;

    public MessageTreeSubViewModel(
            String messageId,
            UserSubViewModel sender,
            String messageBody,
            LocalDateTime messageSentTimestamp,
            List<MessageTreeSubViewModel> replies) {
        this.messageId = messageId;
        this.sender = sender;
        this.messageBody = messageBody;
        this.messageSentTimestamp = messageSentTimestamp;
        this.replies = replies;
    }

    public String getMessageId() {
        return this.messageId;
    }

    public List<MessageTreeSubViewModel> getReplies() {
        return this.replies;
    }

    public UserSubViewModel getSender() {
        return this.sender;
    }

    public String getMessageBody() {
        return this.messageBody;
    }

    public LocalDateTime getMessageSentTimestamp() {
        return this.messageSentTimestamp;
    }

}
