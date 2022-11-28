package entities;

public class SolutionDocFactory {

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
    public static SolutionDocument create(String solutionName,
                                   String solutionId,
                                   Course course,
                                   User user,
                                   Float score,
                                   TestDocument parentTest,
                                   Float recordedTime,
                                   String rootMessageId) {

        return new SolutionDocument(
                solutionName,
                solutionId,
                course,
                user,
                score,
                parentTest,
                recordedTime,
                rootMessageId);
    }
}
