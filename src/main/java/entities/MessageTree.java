package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MessageTree {
    private final Message root;
    private final ArrayList<MessageTree> subtrees;

    public MessageTree(String rootId) {
        this.root = new CommonMessage(rootId, rootId, rootId, rootId, null, LocalDateTime.now());
        this.subtrees = new ArrayList<>();
    }

    private MessageTree(Message root, ArrayList<MessageTree> subtrees) {
        this.root = root;
        this.subtrees = subtrees;
    }

    public boolean  isEmpty(){
        return this.root == null;
    }

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

    public List<MessageTree> getSubtrees(){
        return this.subtrees;
    }

    public Message getRootMessage() {
        return this.root;
    }
}
