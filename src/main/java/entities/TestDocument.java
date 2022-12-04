package entities;

import java.util.HashMap;

public class TestDocument extends Document{

    /**
     * The associated solutions document for the test
     */
    private final HashMap<String, SolutionDocument> solutions;

    /**
     * The time allotted for the test (hrs)
     */
    private Float estimatedTime;

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
     * @param estimatedTime The allotted time to complete the test (hrs)
     * @param numberOfQuestions The number of questions on the test
     * @param testType The type of the test (i.e. Term test, final exam, ...)
     */
    public TestDocument(String name, String id, Course course, User user, Float estimatedTime, int numberOfQuestions, String testType) {
        super(name, id, course, user);
        this.estimatedTime = estimatedTime;
        this.numberOfQuestions = numberOfQuestions;
        this.testType = testType;
        this.solutions = new HashMap<>();

    }

    /**
     * Returns the solutions document for the test
     * @return The SolutionsDocument item
     */
    public HashMap<String, SolutionDocument> getSolutions() {
        return solutions;
    }

    /**
     * Gets the allotted time for the test
     * @return The time for the test
     */
    public Float getEstimatedTime() {
        return estimatedTime;
    }

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

    /**
     * Gets the solution doc by id
     * @param solutionId the solution id of the document to be retrieved
     * @return the solution doc matching the solution id
     */
    public SolutionDocument getSolution(String solutionId) {
        return this.solutions.get(solutionId);
    }

    /**
     * Adds/Updates a solution doc for the test
     * @param solution The SolutionsDocument item to add or update
     */
    public void  addUpdateSolution(SolutionDocument solution) {
        this.solutions.put(solution.getId(), solution);
    }

}
