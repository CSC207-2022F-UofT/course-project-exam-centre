package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Tree {
    private final Message root;
    private final ArrayList<Tree> subtrees;

    public Tree(String rootId) {
        this.root = new CommonMessage(rootId, rootId, rootId, rootId, null, LocalDateTime.now());
        this.subtrees = new ArrayList<Tree>();
    }

    private Tree(Message root, ArrayList<Tree> subtrees) {
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
            this.subtrees.add(new Tree(messsage, new ArrayList<Tree>()));
            return true;
        }
        else{
            for (Tree subtree : this.subtrees){
                if (subtree.addMessage(messsage)){
                    return true;
                }
            }
        }
        return false;
    }
}
