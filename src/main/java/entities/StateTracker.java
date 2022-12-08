package entities;

import java.io.Serializable;
import java.util.*;

public class StateTracker implements Serializable {
    private final Map<String, User> trackedUsers;
    private final Map<String, Course> trackedCourses;
    private Map<String, CourseInfo> courseInfoItems;
    private final Map<String, String> downloadedDocumentPaths;
    private User currentUser;
    private boolean runStatus;

    /**
     * Construct a StateTracker object with no tracked users or tracked courses.
     */
    public StateTracker() {
        this.runStatus = true;
        this.trackedUsers = new HashMap<>();
        this.trackedCourses = new HashMap<>();
        this.courseInfoItems = new HashMap<>();
        this.downloadedDocumentPaths = new HashMap<>();
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
        addUpdateTrackedUser(loggedInUser);
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
        String trackedUserId = trackedUser.getId();
        this.trackedUsers.put(trackedUserId, trackedUser);
    }

    /**
     * Add a new tracked course or update an existing tracked course.
     *
     * @param trackedCourse a Course object to be tracked.
     */
    public void addUpdateTrackedCourse(Course trackedCourse) {
        String trackedCourseId = trackedCourse.getId();
        this.trackedCourses.put(trackedCourseId, trackedCourse);
    }

    /**
     * Set all course info items.
     *
     * @param courseInfoItems a list of all Course info items.
     */
    public void setAllCourseInfoItems(Map<String, CourseInfo> courseInfoItems) {
        this.courseInfoItems = courseInfoItems;
    }

    /**
     * Get all course info items.
     *
     * @return a map of course info items.
     */
    public Map<String, CourseInfo> getAllCourseInfoItems() {
        return this.courseInfoItems;
    }

    /**
     * Get all tracked courses.
     *
     * @return a map of course items.
     */
    public Map<String, Course> getAllTrackedCourses() {
        return this.trackedCourses;
    }

    /**
     * Remove tracked course.
     */
    public void removeTrackedCourse(String courseId) {
        this.trackedCourses.remove(courseId);
    }

    /**
     * Check if a document is already downloaded locally
     * @param documentId the document ID being checked
     * @return whether the document is downloaded locally
     */
    public boolean checkIfDocumentDownloaded(String documentId) {
        return this.downloadedDocumentPaths.containsKey(documentId);
    }

    /**
     * Update or add a local document path to the state tracker
     * @param documentId the document ID being checked
     * @param documentDownloadPath the local path of the downloaded document
     */
    public void updateDownloadedDocuments(
            String documentId,
            String documentDownloadPath) {
        this.downloadedDocumentPaths.put(documentId, documentDownloadPath);
    }

    /**
     * Get the local download path for a document
     * @param documentId the document ID being checked
     * @return a string representing the local document path
     */
    public String getDownloadedDocumentPath(String documentId) {
        return this.downloadedDocumentPaths.get(documentId);
    }

}
