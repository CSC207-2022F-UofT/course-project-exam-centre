package entities;

import java.util.TreeSet;

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
     * The vote tally of the solution doc
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
     * @param recordedTime the recorded time of the solution poster
     */
    public SolutionDocument(String name, String id, Course course, User user, String score, String testID, Float recordedTime, String rootMessageId) {
        super(name, id, course, user);
        this.score = score;
        this.testID = testID;
        this.votes = 0;
        this.recordedTime = recordedTime;
        this.messages = new Tree(rootMessageId);
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

    /**
     * adds a message to the messages tree of the solution
     * @param message the message to be added
     */
    public void addMessage(Message message){
        //Should not need to check if the message does not have a parent id that is in the tree because messages will only be created with parent ids that are already in the tree.
        this.messages.addMessage(message);
    }
}
