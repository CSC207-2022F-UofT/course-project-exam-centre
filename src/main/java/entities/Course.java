package entities;

import java.util.ArrayList;
import java.util.List;

import entities.TestDoc;

/**
 * Represents a university course
 */
public class Course {
    private final String courseName;
    private final String courseCode;
    private final List<TestDoc> tests;
    private final String courseId;

    /** Constructs a new course
     *
     * @param courseName the name of the course
     * @param courseCode the course code of the course
     * @param courseId the assigned id of the course in the database
     */
    public Course(String courseName, String courseCode, String courseId) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.tests = new ArrayList<TestDoc>();
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
    public String getCourseId() {
        return this.courseId;
    }
    /** Gets the course's code
     *
     * @return returs the string corresponding to the course code
     */
    public String getCourseCode() {
        return courseCode;
    }
    /** Adds a test document to the course
     *
     * @param test TestDoc for the test being added
     */
    public void addTest(TestDoc test) {
        this.tests.add(test);
    }
    /** Removes a test document to the course
     *
     * @param test TestDoc for the test being removed
     */
    public void removeTest(TestDoc test) {
        this.tests.remove(test);
    }


}
