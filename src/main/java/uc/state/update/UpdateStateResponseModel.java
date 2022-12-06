package uc.state.update;

import entities.Course;
import entities.CourseInfo;
import entities.User;

import java.util.List;
import java.util.Map;

/** UpdateStateResponseModel is a bundle of data that a presenter can use.
 * @layer use cases
 */
public class UpdateStateResponseModel {
    private User currentUser;
    private List<Course> usersCourses;
    private Map<String, CourseInfo> allCourseInfoItems;

    /**
     * Constructs an UpdateStateResponseModel that contains the currentUser, the courses and a mapping of
     * all the courses to their courseInfo
     *
     * @param currentUser the current user
     * @param usersCourses the users Courses
     * @param allCourseInfo a mapping of courseIds to their courseInfo
     */
    UpdateStateResponseModel(User currentUser, List<Course> usersCourses,
                             Map<String, CourseInfo> allCourseInfo) {

        this.currentUser = currentUser;
        this.usersCourses = usersCourses;
        this.allCourseInfoItems = allCourseInfo;
    }

    /**
     * Gets the current user
     *
     * @return the User entity corresponding to the current user
     */
    User getCurrentUser() {
        return this.currentUser;
    }

    /**
     * Gets the user's courses
     *
     * @return a list of courses that the user is enrolled in
     */
    List<Course> getUsersCourses() {
        return this.usersCourses;
    }

    /**
     * Gets all the courseIds and their respective CourseInfo
     *
     * @return a mapping of the courseId to their CourseInfo
     */
    Map<String, CourseInfo> getAllCourseInfo() {
        return this.allCourseInfoItems;
    }
}
