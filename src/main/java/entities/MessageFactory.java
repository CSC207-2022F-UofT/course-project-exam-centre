package entities;

// Entity layer

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface MessageFactory {
    Message create(int messageId, int solutionId, int userId, int parentId, String Body);
}
