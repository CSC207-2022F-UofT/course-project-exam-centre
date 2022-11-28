package entities;

public class SolutionDocument extends Document {

    /**
     * The decimal format percentage of the estimated score of the solution
     * Precondition: 0.0 <= score <= 1.0
     */
    private Float score;

    /**
     * The recorded time taken by the solution poster (hours).
     */
     private Float recordedTime;

    /**
     * The sum of upvotes and downvotes of the solution document.
     */
    private int votes;

    /**
     * The tree representing the solution document's discussion board messages.
     */
    private MessageTree messageTree;

    /**
     * The parent test document of the solution doc.
     */
    private TestDocument parentTest;

    /**
     * Constructs a new SolutionDocument object.
     *
     * @param solutionName      The name of the document
     * @param solutionId        The document identifier
     * @param course            The course the document belongs to
     * @param user              The user that uploaded the document
     * @param score             The total score of the test
     * @param parentTest        The parent TestDocument object
     * @param rootMessageId     The root message id for this solution document
     * @param recordedTime      The recorded time for the solution document
     */
    public SolutionDocument(
            String solutionName,
            String solutionId,
            Course course,
            User user,
            Float score,
            TestDocument parentTest,
            Float recordedTime,
            String rootMessageId) {

        super(solutionName, solutionId, course, user);
        this.score = score;
        this.parentTest = parentTest;
        this.votes = 0;
        this.recordedTime = recordedTime;
        this.messageTree = new MessageTree(rootMessageId);
    }

    /**
     * Gets the estimated score for the solution set
     * @return The score recorded by the solution poster
     */
    public Float getScore() {
        return score;
    }

    /**
     * Sets the number of marks for the solution
     */
    public void setScore(Float score) {
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
     * Sets the message tree of the solution document
     * @param newMessageTree The new message tree for the solution document
     */
    public void setMessageTree(MessageTree newMessageTree) {
        this.messageTree = newMessageTree;
    }

    /**
     * Gets the message tree for the solution document
     * @return The solution document's message tree
     */
    public MessageTree getMessageTree() {
        return this.messageTree;
    }

    /**
     * Sets the recorded time of the solution document
     * @param newRecordedTime The new recorded time for the solution document
     */
    public void setRecordedTime(Float newRecordedTime) {
        this.recordedTime = newRecordedTime;
    }

    /**
     * Gets the recorded time for the solution document
     * @return The solution document's recorded time
     */
    public Float getRecordedTime() {
        return this.recordedTime;
    }

    /**
     * Sets the parent test document for this solutions document
     * @param parentTest The parent test of the solution
     */
    public void setTest(TestDocument parentTest) {
        this.parentTest = parentTest;
    }

    /**
     * Set solution document vote total
     * @param newVoteTotal The parent test of the solution
     */
    public void setVotes(Integer newVoteTotal) {
        this.votes = newVoteTotal;
    }

    /**
     * adds a message to the messages tree of the solution
     * @param message the message to be added
     */
    public void addMessage(Message message){
        //Should not need to check if the message does not have a parent id that is in the tree because messages will only be created with parent ids that are already in the tree.
        this.messageTree.addMessage(message);
    }
}
