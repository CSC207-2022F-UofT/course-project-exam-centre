package entities;

// Entity layer


import java.time.LocalDateTime;

public interface MessageFactory {
    Message create(String messageId, String solutionId, String userId, String parentId, String Body, LocalDateTime now);
}
