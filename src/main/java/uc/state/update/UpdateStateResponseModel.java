package uc.state.update;

import entities.Course;
import entities.User;

import java.util.List;

public class UpdateStateResponseModel {
    private User currentUser;
    private List<Course> usersCourses;
    private List<Course> allCourseInfoItems;

    UpdateStateResponseModel(User currentUser, List<Course> usersCourses,
                             List<Course> allCourseInfo) {

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

    List<Course> getAllCourseInfo() {
        return this.allCourseInfoItems;
    }
}
