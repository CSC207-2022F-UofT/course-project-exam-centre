package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/** Message Tree represents the tree data structure used to store messages
 * @layer entities
 */
public class MessageTree {
    private final Message root;
    private final ArrayList<MessageTree> subtrees;
    /** Instantiates a MessageTree that contains the message and an ArrayList of MessageTrees
     *
     * @param rootId the rootId of the solution document this message corresponds to.
     * @param solutionId the unique id corresponding to the Solution document the Message is part of.
     * @param userId the unique id corresponding to the creator of the Message.
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
     * Overloaded private constructor used to create a messageTree
     * @param root the root message of the tree
     * @param subtrees the Arraylist of replies to this message.
     */
    private MessageTree(Message root, ArrayList<MessageTree> subtrees) {
        this.root = root;
        this.subtrees = subtrees;
    }

    /**
     * Checks if the tree is empty
     * @return Returns Boolean of whether the tree is empty.
     */
    public boolean  isEmpty(){
        return this.root == null;
    }
    
    /**
     * Adds the given message to the tree
     * @param message the message that needs to get added to the tree
     * @return Returns Boolean of whether the message is added.
     */
    public boolean addMessage(Message message){
        if (this.isEmpty()){
            return false;
        }
        else if (this.root.getMessageId().equals(message.getParentId())){
            this.subtrees.add(new MessageTree(message, new ArrayList<>()));
            return true;
        }
        else{
            for (MessageTree subtree : this.subtrees){
                if (subtree.addMessage(message)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * gets the Subtrees of the message
     * @return Returns List of MessageTree objects that are subtrees to this message.
     */

    public List<MessageTree> getSubtrees(){
        return this.subtrees;
    }

    /**
     * gets the Root Message of this tree
     * @return Returns List of MessageTree objects that are subtrees to this message.
     */
    public Message getRootMessage() {
        return this.root;
    }
}
