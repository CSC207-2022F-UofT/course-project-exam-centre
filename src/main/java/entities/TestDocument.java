package entities;

public class TestDocument extends Document{

    private SolutionDocument solutions;

    private String estimatedTime;

    private int numberOfQuestions;

    private String testType;

    /**
     * Constructs a new Document abstract class for TestDoc or SolutionDoc to extend.
     *
     * @param name   The name of the document
     * @param id     The document identifier
     * @param course The course the document belongs to
     * @param user   The user that uploaded the document
     * @param estimatedTime The alloted time to complete the test
     * @param numberOfQuestions The number of questions on the test
     * @param testType The type of the test (i.e. Term test, final exam, ...)
     */
    public TestDocument(String name, String id, Course course, User user, String estimatedTime, int numberOfQuestions, String testType) {
        super(name, id, course, user);
        this.estimatedTime = estimatedTime;
        this.numberOfQuestions = numberOfQuestions;
        this.testType = testType;

    }

    public SolutionDocument getSolutions() {
        return solutions;
    }

    public void setSolutions(SolutionDocument solutions) {
        this.solutions = solutions;
    }

    public String getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(String estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }
}
