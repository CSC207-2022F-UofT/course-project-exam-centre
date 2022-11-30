package uc.state.update;

import java.time.LocalDateTime;

public interface UpdateStateMessageDbModel {
    String getMessageId();
    String getSolutionId();
    String getUserId();
    String getParentId();
    String getMessageBody();
    LocalDateTime getMessageSentTimestamp();
}
