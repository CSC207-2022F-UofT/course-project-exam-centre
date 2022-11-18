package entities;

// Entity layer

public interface MessageFactory {
    Message create(int messageId, int solutionId, int userId, int parentId, String Body);
}
