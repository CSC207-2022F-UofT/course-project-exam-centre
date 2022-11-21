package entities;

public class SolutionDocument extends Document {

    private String score;

    private String testID;

    private String votes;


    /**
     * Constructs a new Document abstract class for TestDoc or SolutionDoc to extend.
     *
     * @param name   The name of the document
     * @param id     The document identifier
     * @param course The course the document belongs to
     * @param user   The user that uploaded the document
     * @param score  The total score of the test
     * @param testID The UofT id for the test
     * @param votes  The number of votes for the test
     */


    public SolutionDocument(String name, String id, Course course, User user, String score, String testID, String votes) {
        super(name, id, course, user);
        this.score = score;
        this.testID = testID;
        this.votes = votes;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getTestID() {
        return testID;
    }

    public void setTestID(String testID) {
        this.testID = testID;
    }

    public String getVotes() {
        return votes;
    }

    public void setVotes(String votes) {
        this.votes = votes;
    }
}
