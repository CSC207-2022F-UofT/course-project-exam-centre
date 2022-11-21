package entities;

public class TestDocument extends Document{

    /**
     * The associated soltuions document for the test
     */
    private SolutionDocument solutions;

    /**
     * The time allotted for the test
     */
    private String estimatedTime;

    /**
     * The number of questions on the test
     */
    private int numberOfQuestions;

    /**
     * The test type (i.e. quiz, term test, final exam)
     */
    private String testType;

    /**
     * Constructs a new Document abstract class for TestDoc or SolutionDoc to extend.
     *
     * @param name   The name of the document
     * @param id     The document identifier
     * @param course The course the document belongs to
     * @param user   The user that uploaded the document
     * @param estimatedTime The allotted time to complete the test
     * @param numberOfQuestions The number of questions on the test
     * @param testType The type of the test (i.e. Term test, final exam, ...)
     */
    public TestDocument(String name, String id, Course course, User user, String estimatedTime, int numberOfQuestions, String testType) {
        super(name, id, course, user);
        this.estimatedTime = estimatedTime;
        this.numberOfQuestions = numberOfQuestions;
        this.testType = testType;

    }

    /**
     * Returns the solutions document for the test
     * @return The SolutionsDocument item
     */
    public SolutionDocument getSolutions() {
        return solutions;
    }

    /**
     * Adds/Sets the solutions document for the test
     * @param solutions The SolutionsDocument item to add or set
     */
    public void setSolutions(SolutionDocument solutions) {
        this.solutions = solutions;
    }

    /**
     * Gets the allotted time for the test
     * @return The time for the test
     */
    public String getEstimatedTime() {
        return estimatedTime;
    }

    /**
     * Gets the number of questions on the test
     * @return The number of questions on this TestDocument
     */

    /**
     * Gets the number of questions for this test
     * @return The integer number of questions
     */
    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    /**
     * Gets the type of test
     * @return Returns the specified test type
     */
    public String getTestType() {
        return testType;
    }
}
