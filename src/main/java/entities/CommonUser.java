package entities;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/** Represents a given User and their account details.
 *
 */
public class CommonUser implements User {
    private final String firstName;
    private final String lastName;
    private String email;
    private String userId;
    private List<Integer> tests;
    private List<Integer> solutions;
    private List<String> courses;


    /** Constructs a User giving them a firstName,
     * lastName, email, and userId
     *
     * @param firstName First name of a User
     * @param lastName Last name of a User
     * @param email Email of a User
     * @param userId Assigned userId of a User
     */
    public CommonUser(String firstName, String lastName, String email, String userId){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email  = email;
        this.userId = userId;
        this.tests = new ArrayList<Integer>();
        this.solutions = new ArrayList<Integer>();
        this.courses = new LinkedList<String>();
    }

    /** Gets the User's ID number
     *
     * @return returns an 8-digit string relating to the User's ID
     */
    @Override
    public String getUserId() {
        return this.userId;
    }

    /** Gets the User's full name
     *
     * @return returns a string containing the User's first and last name.
     */
    @Override
    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    /** Gets the User's first name
     *
     * @return returns a string containing the User's first name
     */
    @Override
    public String getFirstName(){
        return this.firstName;
    }

    /**Gets the User's Last name
     *
     * @return returns a string containing the User's last name
     */
    @Override
    public String getLastName(){
        return this.lastName;
    }


    /** Gets the User's email
     *
     * @return returns the string of the User's email
     */
    @Override
    public String getEmail(){
        return this.email;
    }

    /** Gets what tests the User has submitted
     *
     * @return returns a list containing the different test doc ids that the User has submitted
     */
    @Override
    public List<Integer> getTests() {
        return tests;
    }

    /** Gets what solutions the User has submitted
     *
     * @return returns a list containing the different solution doc ids that the User has submitted
     */
    @Override
    public List<Integer> getSolutions() {
        return solutions;
    }

    /** Gets what Courses the User has joined
     *
     * @return returns a list containing the Course codes that the User is in
     */
    @Override
    public List<String> getCourses() {
        return courses;
    }

    /** Adds a course to the User's list of courses
     *
     * @param newCourse Course code of the course that is being added
     * @return Returns true if the course was successfully added and false if not
     */
    @Override
    public Boolean addCourse(String newCourse){
        return this.courses.add(newCourse);
    }

    /** Removes a course from the User's list of courses
     *
     * @param courseCode Course code of the course that is being removed
     * @return Returns true if the course was successfully removed and false if not
     */
    @Override
    public Boolean removeCourse(String courseCode){
        return this.courses.remove(courseCode);
    }

    /** Adds a TestDoc to the User's list of TestDocs
     *
     * @param newTestId ID of the new TestDoc that the User has submitted
     * @return Returns true if the test was successfully added and false if not
     */
    @Override
    public Boolean addTest(Integer newTestId){
        return this.tests.add(newTestId);
    }

    /** Adds a solutionDoc to the User's list of TestDocs
     *
     * @param newSolutionId Id of the new SolutionDoc that the User has submitted
     * @return Returns true if the test was successfully added and false if not
     */
    @Override
    public Boolean addSolution(Integer newSolutionId){
        return this.solutions.add(newSolutionId);
    }
}
