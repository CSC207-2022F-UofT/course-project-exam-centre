package entities;

public class MessageTreeFactory {

    /**
     * Returns a new MessageTree object.
     */
    public MessageTree create(String rootMessageId) {
        return new MessageTree(rootMessageId);
    }
}
