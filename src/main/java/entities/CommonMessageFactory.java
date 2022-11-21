package entities;

// Entity layer

public class CommonMessageFactory implements MessageFactory {
    @Override
    public Message create(int messageId, int solutionId, int userId, int parentId, String body) {
        return new CommonMessage(messageId, solutionId, userId, parentId, body);
    }
}
