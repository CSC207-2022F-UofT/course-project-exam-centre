package usecases.course.updatemembers.dbmodels;

import java.time.LocalDateTime;

public interface UpdateCMemMessageDbModel {
    String getMessageId();
    String getSolutionId();
    String getUserId();
    String getParentId();
    String getMessageBody();
    LocalDateTime getMessageSentTimestamp();
}
