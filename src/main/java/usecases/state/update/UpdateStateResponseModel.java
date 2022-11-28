package usecases.state.update;

import entities.Course;
import entities.CourseInfo;
import entities.User;

import java.util.List;
import java.util.Map;

public class UpdateStateResponseModel {
    private User currentUser;
    private List<Course> usersCourses;
    private Map<String, CourseInfo> allCourseInfoItems;

    UpdateStateResponseModel(User currentUser, List<Course> usersCourses,
                             Map<String, CourseInfo> allCourseInfo) {

        this.currentUser = currentUser;
        this.usersCourses = usersCourses;
        this.allCourseInfoItems = allCourseInfo;
    }

    User getCurrentUser() {
        return this.currentUser;
    }

    List<Course> getUsersCourses() {
        return this.usersCourses;
    }

    Map<String, CourseInfo> getAllCourseInfo() {
        return this.allCourseInfoItems;
    }
}
