package uc.doc.submittest;

/**
 * SubmitTDocDsRequestModel bundles the information required for saving a test document into a single object
 * @layer Use cases
 */
public class SubmitTDocDsRequestModel {

    /**
     * The name of this test document
     */
    private final String name;

    /**
     * The number of questions on the test
     */
    private final Integer numberOfQuestions;

    /**
     * The ID of the user submtting the document
     */
    private final String userId;

    /**
     * The ID of the course this test document belongs to
     */
    private final String courseId;

    /**
     * The path of the submitted document on the submitting user's drive
     */
    private final String filePath;

    /**
     * The time limit for this test
     */
    private final Float recordedTime;

    /**
     * The type of test this is (i.e. quiz, term test, final exam)
     */
    private final String testType;

    /**
     * Creates a new instance of the test document submission data storage request model for storing the needed
     * information in persistent memory
     * @param name The name of the test document
     * @param numberOfQuestions The number of question on this test
     * @param estimatedTime The time limit for this test
     * @param testType The type of test this is (i.e. quiz, term test, final exam)
     * @param userID The ID of the user submitting this document
     * @param courseID The ID of the course this test belongs to
     * @param filePath The path to the file on the user's drive
     */
    public SubmitTDocDsRequestModel(String name,
                                    Integer numberOfQuestions,
                                    Float estimatedTime,
                                    String testType,
                                    String userID,
                                    String courseID,
                                    String filePath) {
        this.name = name;
        this.numberOfQuestions = numberOfQuestions;
        this.userId = userID;
        this.courseId = courseID;
        this.filePath = filePath;
        this.recordedTime = estimatedTime;
        this.testType = testType;
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
     * Gets the ID of the user who submitted this document
     * @return The user's ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Gets the ID of the course this test document belongs to
     * @return The course's ID
     */
    public String getCourseId() {
        return courseId;
    }

    /**
     * Gets the absolute path to the file on the submitting user's drive
     * @return The file path
     */
    public String getFilePath() {
        return filePath;
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
}
