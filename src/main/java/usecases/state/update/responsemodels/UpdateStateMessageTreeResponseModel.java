package usecases.state.update.responsemodels;

import java.time.LocalDateTime;
import java.util.List;

public class UpdateStateMessageTreeResponseModel {

    private final String messageId;
    private final UpdateStateUserResponseModel sender;
    private final String messageBody;
    private final LocalDateTime messageSentTimestamp;
    private final List<UpdateStateMessageTreeResponseModel> replies;

    public UpdateStateMessageTreeResponseModel(
            String messageId,
            UpdateStateUserResponseModel sender,
            String messageBody,
            LocalDateTime messageSentTimestamp,
            List<UpdateStateMessageTreeResponseModel> replies) {
        this.messageId = messageId;
        this.sender = sender;
        this.messageBody = messageBody;
        this.messageSentTimestamp = messageSentTimestamp;
        this.replies = replies;
    }

    public String getMessageId() {
        return this.messageId;
    }

    public List<UpdateStateMessageTreeResponseModel> getReplies() {
        return this.replies;
    }

    public UpdateStateUserResponseModel getSender() {
        return this.sender;
    }

    public String getMessageBody() {
        return this.messageBody;
    }

    public LocalDateTime getMessageSentTimestamp() {
        return this.messageSentTimestamp;
    }

}
