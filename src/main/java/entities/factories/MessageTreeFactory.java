package entities.factories;

import entities.MessageTree;

public class MessageTreeFactory {

    /**
     * Returns a new MessageTree object.
     */
    public MessageTree create(String rootMessageId) {
        return new MessageTree(rootMessageId);
    }
}
