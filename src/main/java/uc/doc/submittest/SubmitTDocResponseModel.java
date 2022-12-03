package uc.doc.submittest;

import java.time.LocalDateTime;

public class SubmitTDocResponseModel {

    /**
     * The ID of the newly created test doucment
     */
    private final String testDocID;

    /**
     * The time that the document was created
     */
    private final LocalDateTime timestamp;

    /**
     * Creates a new response model for the test document submission use case for giving relevant information back to
     * the user
     * @param testDocID The ID of the created test document
     * @param timestamp The time the document was created
     */
    public SubmitTDocResponseModel(String testDocID, LocalDateTime timestamp) {
        this.testDocID = testDocID;
        this.timestamp = timestamp;
    }

    /**
     * Gets the ID of the new test document
     * @return The ID of the test document
     */
    public String getTestDocID() {
        return testDocID;
    }

    /**
     * Gets the time that the document was created
     * @return The timestamp in a LocalDateTime object
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
