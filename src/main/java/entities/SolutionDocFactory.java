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
     * @param testDocument The parent test doc for the solution
     */
    public static SolutionDocument create(String name, String id, Course course,
                                   User user, Integer score,TestDocument testDocument,
                                          Float recordedTime) {
        return new SolutionDocument(name, id, course, user, score, testDocument, recordedTime);
    }
}
