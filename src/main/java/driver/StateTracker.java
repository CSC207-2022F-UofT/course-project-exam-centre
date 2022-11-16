package driver;

import java.io.Serializable;
import java.util.*;

import entities.Course;
import entities.User;

public class StateTracker implements Serializable {
    private final HashMap<String, User> trackedUsers;
    private final HashMap<String, Course> trackedCourses;
    private User currentUser;
    private boolean runStatus;

    /**
     * Construct a StateTracker object with no tracked users or tracked courses.
     */
    public StateTracker() {
        this.runStatus = true;
        this.trackedUsers = new HashMap<>();
        this.trackedCourses = new HashMap<>();
        this.currentUser = null;
    }

    /**
     * Gets the current run status of the program.
     *
     * @return whether the program should continue running or not.
     */
    public boolean getRunStatus() {
        return this.runStatus;
    }

    /**
     * Changes the run status to false, indicating that the program should not
     * continue running.
     */
    public void stopRunning() {
        this.runStatus = false;
    }

    /**
     * Gets the current logged-in user if one exits.
     *
     * @return the currently logged-in User object if a user is logged in, or null if
     * no user is logged in.
     */
    public User getCurrentUser() {
        return this.currentUser;
    }

    /**
     * Sets the current user to null to indicate that the user is logged out.
     */
    public void removeCurrentUser() {
        this.currentUser = null;
    }

    /**
     * Sets the current user to the given User object.
     *
     * @param loggedInUser the User object that will be set as the current user.
     */
    public void setCurrentUser(User loggedInUser) {
        this.currentUser = loggedInUser;
    }

    /**
     * Gets the User object corresponding to the given userId if the user is tracked.
     *
     * @param userId a string representing the unique user ID for a user.
     * @return a User object corresponding to the userId.
     */
    public User getUserIfTracked(String userId) {
        return this.trackedUsers.get(userId);
    }

    /**
     * Check if a user is tracked from its user ID.
     *
     * @param userId a string representing the unique user ID for a user.
     * @return whether the user corresponding to the userId is tracked.
     */
    public boolean checkIfUserTracked(String userId) {
        return this.trackedUsers.containsKey(userId);
    }

    /**
     * Gets the Course object corresponding to the given courseId if the user is
     * tracked.
     *
     * @param courseId a string representing the unique course ID for a course.
     * @return a Course object corresponding to the courseId.
     */
    public Course getCourseIfTracked(String courseId) {
        return this.trackedCourses.get(courseId);
    }

    /**
     * Check if a course is tracked from its course ID.
     *
     * @param courseId a string representing the unique course ID for a course.
     * @return whether the course corresponding to the courseId is tracked.
     */
    public boolean checkIfCourseTracked(String courseId) {
        return this.trackedCourses.containsKey(courseId);
    }

    /**
     * Add a new tracked user or update an existing tracked user.
     *
     * @param trackedUser a User object to be tracked or updated.
     */
    public void addUpdateTrackedUser(User trackedUser) {
        String trackedUserId = trackedUser.getUserId();
        this.trackedUsers.put(trackedUserId, trackedUser);
    }

    /**
     * Add a new tracked course or update an existing tracked course.
     *
     * @param trackedCourse a Course object to be tracked.
     */
    public void addUpdateTrackedCourse(Course trackedCourse) {
        String trackedUserId = trackedCourse.getCourseId();
        this.trackedCourses.put(trackedUserId, trackedCourse);
    }

}
