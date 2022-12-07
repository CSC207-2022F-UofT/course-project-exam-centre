package fworks.views;

import java.util.HashMap;

public interface IUpdateCourseMembershipScreen {

    void reset();

    void createScreenWithCourses(HashMap<String, String> courses, String userID);

}
