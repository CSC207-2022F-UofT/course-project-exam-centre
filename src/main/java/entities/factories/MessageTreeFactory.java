package entities.factories;

import entities.MessageTree;

public class MessageTreeFactory {

    /** Creates a new MessageTree
     *
     * @param rootMessageId unique identifier for the root message id
     * @param solutionId unique identifier to the solution document corresponding to the message tree.
     * @param userId unique identifier of a user.
     * @return a new MessageTree object.
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
