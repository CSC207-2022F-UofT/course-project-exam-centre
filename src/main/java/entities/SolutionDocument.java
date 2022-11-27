package entities;

import java.util.TreeSet;

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
     * The associated messages for the solutions doc
     */
    private Tree messages;

    /**
     * The rootMessage of all the messages held in the messages tree.
     */
    private String rootMessageId;





    /**
     * Constructs a new Document abstract class for TestDoc or SolutionDoc to extend.
     *
     * @param name   The name of the document
     * @param id     The document identifier
     * @param course The course the document belongs to
     * @param user   The user that uploaded the document
     * @param score  The total score of the test
     * @param testID The UofT id for the test
     * @param rootMessageId the rootMessage for this solution document that will hold all the messages for the solution
     */
    public SolutionDocument(String name, String id, Course course, User user, String score, String testID, String rootMessageId) {
        super(name, id, course, user);
        this.score = score;
        this.testID = testID;
        this.votes = 0;
        this.messages = new Tree(rootMessageId);
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

    /**
     * adds a message to the messages tree of the solution
     * @param message the message to be added
     */
    public void addMessage(Message message){
        //Should not need to check if the message does not have a parent id that is in the tree because messages will only be created with parent ids that are already in the tree.
        this.messages.addMessage(message);
    }
}
