package uc.doc.submitsolution;

public class SubSDocDsRequestModel {

    /**
     * Name of the document
     */
    private final String name;

    /**
     * The number of marks earned for this solution set
     */
    private final Float recordedScore;

    /**
     * The ID of the user submitting this solution document
     */
    private final String userId;

    /**
     * The ID of the course this document corresponds to
     */
    private final String courseId;

    /**
     * The ID of the test that this solution document corresponds to
     */
    private final String parentTestId;

    /**
     * The ID of the root message for the discussion board under this solution document
     */
    private final String rootMessageId;

    /**
     * The total number of upvotes + downvotes for this solution document
     */
    private final Integer voteTotal;

    /**
     * The path to the file on the submitting user's drive
     */
    private final String filePath;

    /**
     * The estimated time it took to write this solution set
     */
    private final Float estimatedTime;

    /**
     * Creates a new solution doc submission data storage model used to store the solution doc in a database
     * @param name The name of the document
     * @param userId The ID of the user submitting the document
     * @param recordedScore The marks earned for this solution document
     * @param courseId The ID of the course for which this solution document is in
     * @param parentTestId The ID of the test which this solution document corresponds to
     * @param filePath The path to the file on the submitting user's drive
     * @param rootMessageId The ID of the root message for the discussion board attached to this solution document
     * @param voteTotal The total number of upvotes and downvotes for this document
     * @param estimatedTime The estimated time it took to write this solution set
     */
    public SubSDocDsRequestModel(
            String name,
            String userId,
            Float recordedScore,
            String courseId,
            String parentTestId,
            String filePath,
            String rootMessageId,
            Integer voteTotal,
            Float estimatedTime) {

        this.name = name;
        this.recordedScore = recordedScore;
        this.userId = userId;
        this.courseId = courseId;
        this.parentTestId = parentTestId;
        this.filePath = filePath;
        this.rootMessageId = rootMessageId;
        this.voteTotal = voteTotal;
        this.estimatedTime = estimatedTime;
    }

    /**
     * Gets the recorded score (marks earned) for this solution document
     * @return The number of marks
     */
    public Float getRecordedScore() {
        return recordedScore;
    }

    /**
     * Gets the time it took to write this solution set
     * @return The estimated time
     */
    public Float getEstimatedTime() {
        return estimatedTime;
    }

    /**
     * Gets the number of upvotes and downvotes for this solution document
     * @return The number of upvotes + downvotes
     */
    public Integer getVoteTotal() {
        return voteTotal;
    }

    /**
     * Gets the ID of the root message for the attached discussion board
     * @return The ID of the root message
     */
    public String getRootMessageId() {
        return rootMessageId;
    }

    /**
     * Gets the ID of the user which submitted this document
     * @return The user's ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Gets the ID of the course this solution document corresponds to
     * @return The course's ID
     */
    public String getCourseId() {
        return courseId;
    }

    /**
     * Gets the ID of the test corresponding to this solution document
     * @return
     */
    public String getParentTestId() {
        return parentTestId;
    }

    /**
     * Gets the path to the submitted document on the user's drive
     * @return The absolute file path
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Gets the name of the document specified when submitting
     * @return The document's name
     */
    public String getName() {
        return name;
    }
}
