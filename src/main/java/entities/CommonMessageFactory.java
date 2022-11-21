package entities;

// Entity layer

<<<<<<< HEAD
=======
import java.time.LocalDateTime;
import java.util.ArrayList;

>>>>>>> origin/8-use-case-8-submit-discussion-board-message
public class CommonMessageFactory implements MessageFactory {
    @Override
    public Message create(int messageId, int solutionId, int userId, int parentId, String body) {
        return new CommonMessage(messageId, solutionId, userId, parentId, body);
    }
}
