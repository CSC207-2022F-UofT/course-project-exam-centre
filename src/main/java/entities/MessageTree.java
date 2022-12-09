package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class MessageTree {
    private final Message root;
    private final ArrayList<MessageTree> subtrees;

    /** Constructs a new MessageTree object
     *
     * @param rootId the id of the root message of the tree.
     * @param solutionId the id of the solution document corresponding to the MessageTree.
     * @param userId the id of the user sending the message
     */
    public MessageTree(String rootId,
                       String solutionId,
                       String userId) {
        this.root = new CommonMessage(rootId,
                solutionId,
                userId,
                null,
                "",
                LocalDateTime.now());
        this.subtrees = new ArrayList<>();
    }

    /**
     *
     * @param root
     * @param subtrees the subtrees corresponding to replies of the message
     */
    private MessageTree(Message root, ArrayList<MessageTree> subtrees) {
        this.root = root;
        this.subtrees = subtrees;
    }

    /** Checks whether the tree is empty.
     *
     * @return returns whether a
     */
    public boolean  isEmpty(){
        return this.root == null;
    }

    /** Adds another message to the MessageTree.
     *
     * @param messsage another message
     * @return whether adding the message is successful or not.
     */
    public boolean addMessage(Message messsage){
        if (this.isEmpty()){
            return false;
        }
        else if (this.root.getMessageId().equals(messsage.getParentId())){
            this.subtrees.add(new MessageTree(messsage, new ArrayList<>()));
            return true;
        }
        else{
            for (MessageTree subtree : this.subtrees){
                if (subtree.addMessage(messsage)){
                    return true;
                }
            }
        }
        return false;
    }

    /** Gets the subtrees of the MessageTree
     *
     * @return return the subtrees.
     */
    public List<MessageTree> getSubtrees(){
        return this.subtrees;
    }

    /** Gets the root message of the tree.
     *
     * @return returns the root of the tree
     */
    public Message getRootMessage() {
        return this.root;
    }
}
