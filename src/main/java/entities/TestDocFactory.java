package entities;

public class TestDocFactory {


    /**
     * Returns a new Document abstract class for TestDoc or SolutionDoc to extend.
     *
     * @param name   The name of the document
     * @param id     The document identifier
     * @param course The course the document belongs to
     * @param user   The user that uploaded the document
     * @param estimatedTime The alloted time to complete the test
     * @param numberOfQuestions The number of questions on the test
     * @param testType The type of the test (i.e. Term test, final exam, ...)
     */
    public static TestDocument create (String name, String id, Course course, User user, Float estimatedTime, int numberOfQuestions, String testType) {
        return new TestDocument(name, id, course, user, estimatedTime, numberOfQuestions, testType);
    }
}
