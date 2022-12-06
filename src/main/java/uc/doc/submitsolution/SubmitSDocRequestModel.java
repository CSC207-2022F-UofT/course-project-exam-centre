package uc.doc.submitsolution;

/**
 * SubSDocRequestModel is a collection of the data required by the interactor in a single object
 * @layer Use cases
 */

public class SubmitSDocRequestModel {

    /**
     * The name of the document
     */
    private final String name;

    /**
     * The path to the file on the submitting user's drive
     */
    private final String filePath;

    /**
     * The number of marks earned for this solution set
     */
    private final Float recordedScore;

    /**
     * The ID of the course this document corresponds to
     */
    private final String courseID;

    /**
     * The estimated time it took to write this solution set
     */
    private final Float recordedTime;

    /**
     * The ID of the test that this solution document corresponds to
     */
    private final String parentTestID;

    /**
     * Creates a SubmitSDocRequestModel instance which is used to pass the needed information into the interactor for
     * creation of a new SolutionDoc entity
     * @param name The name of the document
     * @param filePath The path to the document on the submitting user's drive
     * @param recordedScore The number of marks earned from this solution set
     * @param courseID The ID of the course which this solution doc corresponds to
     * @param recordedTime The estimated time it took to write this solution set
     * @param parentTest The ID of the test this solution document was written for
     */
    public SubmitSDocRequestModel(String name,
                                  String filePath,
                                  Float recordedScore,
                                  String courseID,
                                  Float recordedTime,
                                  String parentTest) {
        this.name = name;
        this.filePath = filePath;
        this.recordedScore = recordedScore;
        this.courseID = courseID;
        this.recordedTime = recordedTime;
        this.parentTestID = parentTest;
    }

    /**
     * Gets the path to the submitted document on the user's drive
     * @return The absolute file path
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Gets the recorded score (marks earned) for this solution document
     * @return The number of marks
     */
    public Float getRecordedScore() {
        return recordedScore;
    }

    /**
     * Gets the ID of the course this solution document corresponds to
     * @return The course's ID
     */
    public String getCourseID() {
        return courseID;
    }

    /**
     * Gets the time it took to write this solution set
     * @return The estimated time
     */
    public Float getRecordedTime() {
        return recordedTime;
    }

    /**
     * Gets the name of the document specified when submitting
     * @return The document's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the ID of the test corresponding to this solution document
     * @return
     */
    public String getParentTestID() {
        return parentTestID;
    }
}
