package ia.controllers;

import uc.doc.submittest.SubTDocRequestModel;
import uc.doc.submittest.SubmitTDocInputBoundary;
import uc.doc.submittest.SubmitTDocResponseModel;

public class SubmitTestDocController {

    /**
     * The input boundary for the Submit Test Doc use case
     */
    private final SubmitTDocInputBoundary inputBoundary;

    /**
     * Creates an instance of SubmitTestDocController for taking information from the related view and sending that
     * info to the use case
     * @param inputBoundary An instance of SubmitTDocInputBoundary for the SubmitTestDoc use case
     */
    public SubmitTestDocController(SubmitTDocInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    /**
     * Takes in information from the corresponding view, parses it into an instance of the use case's request model, and
     * returns a response model.
     * @param name The name of the document
     * @param numOfQuestions The number of questions on the test
     * @param recordedTime The time limit for the test
     * @param testType The type of test (i.e. quiz, term test, final exam)
     * @param userID The ID of the user submitting this document
     * @param courseID The ID of the course this document belongs to
     * @param filePath The path to the document on the user's drive
     * @return An instance of a SubmitTDocResponseModel for this request
     */
    public SubmitTDocResponseModel SubmitTestDocument(String name,
                               Integer numOfQuestions,
                               Float recordedTime,
                               String testType,
                               String userID,
                               String courseID,
                               String filePath) {
        SubTDocRequestModel requestModel = new SubTDocRequestModel(
                name,
                numOfQuestions,
                recordedTime,
                testType,
                userID,
                courseID,
                filePath
        );
    return inputBoundary.submitTestDoc(requestModel);
    }
}
