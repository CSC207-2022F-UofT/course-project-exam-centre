package entities;

import java.util.*;

/**
 * Represents a university course
 */
public class Course implements CourseInfo {
    private final String courseName;
    private final String courseCode;
    private final Map<String, TestDocument> tests;
    private final String courseId;

    /**
     * Constructs a new course object
     *
     * @param courseName the name of the course
     * @param courseCode the course code of the course
     * @param courseId the assigned id of the course in the database
     */
    public Course(String courseName, String courseCode, String courseId) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.tests = new HashMap<>();
        this.courseId = courseId;

    }
    /** Gets the course's name
     *
     * @return returns the course name/title
     */
    public String getCourseName() {
        return courseName;
    }

    /** Gets the course id designated to the course
     *
     * @return returns the courseId
     */
    public String getId() {
        return this.courseId;
    }

    /** Gets the course's code
     *
     * @return returs the string corresponding to the course code
     */
    public String getCourseCode() {
        return courseCode;
    }

    /** Gets the course's code
     *
     * @return the string corresponding to the course code
     */
    public Map<String, TestDocument> getTests() {
        return this.tests;
    }

    /** Gets the course's code
     *
     * @return the string corresponding to the course code
     */
    public TestDocument getTest(String testId) {
        return this.tests.get(testId);
    }

    /** Adds a test document to the course
     *
     * @param test TestDoc for the test being added
     */
    public void addTest(TestDocument test) {
        this.tests.put(test.getId(), test);
    }

    /** Removes a test document to the course
     *
     * @param testId The id of the test document being removed
     */
    public void removeTestById(String testId) {
        this.tests.remove(testId);
    }


}
