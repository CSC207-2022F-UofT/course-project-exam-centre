package ia.viewmodels;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CourseMembershipViewModel {

    private Map<String, List<Object>> courses;

    private String userID;

    /**
     * Creates a viewmodel containing the needed information for displaying a users courses
     * @param courses A HashMap with the courses' ID as a key, and list containing the course name, and the user's
     *                registration status
     * @param userID The users' ID
     */
    public CourseMembershipViewModel(Map<String, List<Object>> courses, String userID) {
        this.userID = userID;
        this.courses = courses;
    }


    public Map<String, List<Object>> getCourses() {
        return courses;
    }

    public ArrayList<String> getCurrentCourses() {
        ArrayList<String> courseList = new ArrayList<>();
        for (List<Object> course : courses.values()) {
            if ((boolean) course.get(1)) {
                courseList.add((String) course.get(0));
            }
        }
        return courseList;
    }

    public void setCourses(Map<String, List<Object>> courses) {
        this.courses = courses;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
