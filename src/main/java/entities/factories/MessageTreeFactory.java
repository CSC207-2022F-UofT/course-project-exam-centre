package entities.factories;

import entities.MessageTree;

public class MessageTreeFactory {

    /**
     * Returns a new MessageTree object.
     */
    public MessageTree create(String rootMessageId,
                              String solutionId,
                              String userId) {
        return new MessageTree(
                rootMessageId,
                solutionId,
                userId);
    }
}
