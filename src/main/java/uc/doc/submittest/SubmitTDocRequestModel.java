package uc.doc.submittest;

/**
 * SubmitTDocRequestModel bundles the data required by the interactor into a single object
 * @layer Use cases
 */
public class SubmitTDocRequestModel {

    private final String name;
    private final Integer numberOfQuestions;
    private final Float recordedTime;
    private final String testType;
    private final String courseID;
    private final String filePath;

    /**
     * Creates a new request model for the test document submission use case, passing in needed information
     * @param name The name of the test document
     * @param numberOfQuestions The number of questions on the test
     * @param recordedTime The time limit allotted for this test
     * @param testType The type of test this document is (i.e. quiz, term test, final exam)
     * @param courseID The ID of the course this test belongs to
     * @param filePath The path of the document on the user's drive
     */
    public SubmitTDocRequestModel(String name,
                                  Integer numberOfQuestions,
                                  Float recordedTime,
                                  String testType,
                                  String courseID,
                                  String filePath) {
        this.name = name;
        this.numberOfQuestions = numberOfQuestions;
        this.recordedTime = recordedTime;
        this.testType = testType;
        this.courseID = courseID;
        this.filePath = filePath;
    }
    /**
     * Gets the absolute path to the file on the submitting user's drive
     * @return The file path
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Gets the name of the test document
     * @return The name of the document
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the number of questions on the test
     * @return The number of questions
     */
    public Integer getNumberOfQuestions() {
        return numberOfQuestions;
    }

    /**
     * Gets the time limit allotted for this test
     * @return The time limit
     */
    public Float getRecordedTime() {
        return recordedTime;
    }

    /**
     * Gets the type of test this was (i.e. quiz, term test, final exam)
     * @return The test type
     */
    public String getTestType() {
        return testType;
    }

    /**
     * Gets the ID of the course this test document belongs to
     * @return The course's ID
     */
    public String getCourseID() {
        return courseID;
    }
}
