package ia.controllers;

import uc.doc.submitsolution.SubmitSDocRequestModel;
import uc.doc.submitsolution.SubmitSDocResponseModel;
import uc.doc.submitsolution.SubmitSDocInputBoundary;

/**
 * SubmitSolutionDocController provides an entry way into the SubmitSolutionDoc use case
 * @layer interface adapters
 */
public class SubmitSolutionDocController{

    /**
     * The input boundary class for the Submit document use case
     */
    private final SubmitSDocInputBoundary inputBoundary;

    /**
     * Creates a new instance of SubmitSolutionDocController to take in data from the related screen
     * @param inputBoundary The SubmitSDocInputBoundary from the Submit Document use case
     */
    public SubmitSolutionDocController(SubmitSDocInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    /**
     * Takes in the information from the related screen, sends that information in an instance of
     * SubmitSDocInputBoundary to the use case, and returns the related response model
     * @param name Name of the document
     * @param recordedScore The number of marks for the document
     * @param courseID The ID of the related course
     * @param recordedTime The time limit for the associated test
     * @param parentTestID The ID of the corresponding test
     * @param filePath The path to document on the user's computer
     * @return A SubmitSDocResponseModel object
     */
    public SubmitSDocResponseModel SubmitSolutionDoc(String name,
                                                     Float recordedScore,
                                                     String courseID,
                                                     Float recordedTime,
                                                     String parentTestID,
                                                     String filePath) {

        SubmitSDocRequestModel requestModel = new SubmitSDocRequestModel(
                name,
                filePath,
                recordedScore,
                courseID,
                recordedTime,
                parentTestID
                );

        return inputBoundary.submitSolutionDoc(requestModel);
    }
}
