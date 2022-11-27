package entities;

public class SolutionDocFactory {


    /**
     * Constructs a new Document abstract class for TestDoc or SolutionDoc to extend.
     *
     * @param name   The name of the document
     * @param id     The document identifier
     * @param course The course the document belongs to
     * @param user   The user that uploaded the document
     * @param score  The total score of the test
     * @param testID The UofT id for the test
     * @param rootID The rootID that hold all the messages for this solution.
     * @param recordedTime the recorded time of the solution poster
     */
    public SolutionDocument create(String name, String id, Course course, User user, String score, String testID, Float recordedTime, String rootID) {
        return new SolutionDocument(name, id, course, user, score, testID, recordedTime, rootID);
    }
}
