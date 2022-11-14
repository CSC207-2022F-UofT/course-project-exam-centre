package driver;

import java.io.Serializable;
import java.util.*;
import entities.User;
import entities.Course;

public class StateTracker implements Serializable {
    private final HashMap<String, User> userMap;
    private final HashMap<String, Course> courseMap;
    private User currentUser;
    private boolean runStatus;

    public StateTracker() {
        this.runStatus = true;
        this.userMap = new HashMap<>();
        this.courseMap = new HashMap<>();
        this.currentUser = null;
    }

    public boolean getRunStatus() {
        return this.runStatus;
    }

}
