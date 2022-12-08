package fworks.views;

import java.util.HashMap;
import java.util.List;

public class CourseMembershipViewModel {

    private HashMap<String, List<Object>> courses;

    private String userID;

    /**
     * Creates a viewmodel containing the needed information for displaying a users courses
     * @param courses A HashMap with the courses' ID as a key, and list containing the course name, and the user's
     *                registration status
     * @param userID The users' ID
     */
    public CourseMembershipViewModel(HashMap<String, List<Object>> courses, String userID) {
        this.userID = userID;
        this.courses = courses;
    }


    public HashMap<String, List<Object>> getCourses() {
        return courses;
    }

    public void setCourses(HashMap<String, List<Object>> courses) {
        this.courses = courses;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
