package uc.doc.submittest;

/**
 * SubmitTDocRequestModel bundles the data required by the interactor into a single object
 * @layer Use cases
 */
public class SubmitTDocRequestModel {

    /**
     * The name of this test document
     */
    private final String name;

    /**
     * The number of questions on the test
     */
    private final Integer numberOfQuestions;

    /**
     * The time limit for this test
     */
    private final Float recordedTime;

    /**
     * The type of test this is (i.e. quiz, term test, final exam)
     */
    private final String testType;

    /**
     * The ID of the user submtting the document
     */
    private final String userID;

    /**
     * The ID of the course this test document belongs to
     */
    private final String courseID;

    /**
     * The path of the submitted document on the submitting user's drive
     */
    private final String filePath;

    /**
     * Creates a new request model for the test document submission use case, passing in needed information
     * @param name The name of the test document
     * @param numberOfQuestions The number of questions on the test
     * @param recordedTime The time limit allotted for this test
     * @param testType The type of test this document is (i.e. quiz, term test, final exam)
     * @param userID The ID of the user submitting this document
     * @param courseID The ID of the course this test belongs to
     * @param filePath The path of the document on the user's drive
     */
    public SubmitTDocRequestModel(String name,
                                  Integer numberOfQuestions,
                                  Float recordedTime,
                                  String testType,
                                  String userID,
                                  String courseID,
                                  String filePath) {
        this.name = name;
        this.numberOfQuestions = numberOfQuestions;
        this.recordedTime = recordedTime;
        this.testType = testType;
        this.userID = userID;
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
     * Gets the ID of the user who submitted this document
     * @return The user's ID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Gets the ID of the course this test document belongs to
     * @return The course's ID
     */
    public String getCourseID() {
        return courseID;
    }
}
