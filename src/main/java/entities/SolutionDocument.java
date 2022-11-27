package entities;

public class SolutionDocument extends Document {

    /**
     * The total number of marks for the exam
     */
    private Integer score;

    /**
     * The parent test object
     */
    private TestDocument parentTest;

    /**
     * The recorded time taken by the solution poster
     */
    private Float recordedTime;

    /**
     * Constructs a new Document abstract class for TestDoc or SolutionDoc to extend.
     *
     * @param name   The name of the document
     * @param id     The document identifier
     * @param course The course the document belongs to
     * @param user   The user that uploaded the document
     * @param score  The total score of the solution set
     * @param testDocument The parent test document for the solution
     */
    public SolutionDocument(String name, String id, Course course,
                            User user, Integer score, TestDocument testDocument,
                            Float recordedTime) {
        super(name, id, course, user);
        this.score = score;
        this.parentTest = testDocument;
        this.recordedTime = recordedTime;
    }

    /**
     * Gets the number of marks for the solution
     * @return The score recorded by the solution poster
     */
    public Integer getScore() {
        return score;
    }

    /**
     * Sets the number of marks for the solution
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * Gets the parent test object
     * @return The parent test object
     */
    public TestDocument getTest() {
        return parentTest;
    }

    /**
     * Sets the parent test document for this solutions document
     * @param parentTest The parent test of the solution
     */
    public void setTest(TestDocument parentTest) {
        this.parentTest = parentTest;
    }
}
