package entities;

public class SolutionDocument extends Document {

    /**
     * The total number of marks for the exam
     */
    private String score;

    /**
     * The course's ID for the test
     */
    private String testID;

    /**
     * The number of votes that the solution document has
     */
    private int votes;

    /**
     * The associated test document for the solutions doc
     */
    private TestDocument document;


    /**
     * Constructs a new Document abstract class for TestDoc or SolutionDoc to extend.
     *
     * @param name   The name of the document
     * @param id     The document identifier
     * @param course The course the document belongs to
     * @param user   The user that uploaded the document
     * @param score  The total score of the test
     * @param testID The UofT id for the test
     */
    public SolutionDocument(String name, String id, Course course, User user, String score, String testID) {
        super(name, id, course, user);
        this.score = score;
        this.testID = testID;
        this.votes = 0;
    }

    /**
     * Gets the number of marks for the exam
     * @return The total number of marks
     */
    public String getScore() {
        return score;
    }

    /**
     * Gets the id for the test
     * @return The test's id
     */
    public String getTestID() {
        return testID;
    }

    /**
     * Gets how many votes the solution document has
     * @return The number of votes for the document
     */
    public int getVotes() {
        return votes;
    }

    /**
     * Sets the number of votes for the document
     * @param votes The new number of votes
     */
    public void setVotes(int votes) {
        this.votes = votes;
    }

    /**
     * Gets the associated test document for the solutions doc
     * @return The TestDocument item
     */
    public TestDocument getDocument() {
        return document;
    }

    /**
     * Adds/sets an associated test document for this solutions document
     * @param document The TestDocument item
     */
    public void setDocument(TestDocument document) {
        this.document = document;
    }
}
