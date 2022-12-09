package entities;

import java.util.List;

/** User entity that represents a User of the program
 * @layer entities
 */
public interface User {

    /**
     * Gets the id of the user
     * @return the String corresponding to the testId
     */
    String getId();

    /**
     * Gets the Full Name of the User
     * @return the String corresponding to the Full Name of the user
     */
    String getName();

    /**
     * Gets the First Name of the user
     * @return the String corresponding to the First Name of the user
     */
    String getFirstName();

    /**
     * Gets the Last Name of the user
     * @return the String corresponding to the Last Name of the user
     */
    String getLastName();
    /**
     * Gets the Email of the user
     * @return the String corresponding to the Email of the user
     */

    String getEmail();

    /**
     * Gets the tests the user has uploaded
     * @return the List of strings corresponding to the tests they uploaded
     */
    List<String> getTests();

    /**
     * Gets the solutions the user has uploaded
     * @return the List of strings corresponding to the tests they uploaded
     */
    List<String> getSolutions();

    /**
     * Gets the Courses the user is apart of
     * @return the List of strings corresponding to the courses the user is apart of
     */
    List<String> getCourses();

    /**
     * Adds a new course that the user is taking.
     * @param newCourse the course to be added to the users list of courses
     * @return the boolean that confirms whether the course is added.
     */
    Boolean addCourse(String newCourse);

    /**
     * Removes a course that the user is taking.
     * @param courseCode the course to be removed from the users list of courses
     * @return the boolean that confirms whether the course is removed.
     */
    Boolean removeCourse(String courseCode);

    /**
     * Adds a new test id that corresponds to the test that the user has uploaded
     * @param newTestId the test id of the test the user has added.
     * @return the boolean that confirms whether the test Id has been added.
     */
    Boolean addTest(String newTestId);

    /**
     * Adds a new solution id that corresponds to the solution that the user has uploaded
     * @param newSolutionId the solution id of the solution the user has added.
     * @return the boolean that confirms whether the solution Id has been added.
     */
    Boolean addSolution(String newSolutionId);
}
