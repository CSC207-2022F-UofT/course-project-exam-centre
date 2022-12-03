package ia.controllers;

import uc.doc.submittest.SubTDocRequestModel;
import uc.doc.submittest.SubmitTDocInputBoundary;
import uc.doc.submittest.SubmitTDocResponseModel;

public class SubmitTestDocController {

    private final SubmitTDocInputBoundary inputBoundary;

    public SubmitTestDocController(SubmitTDocInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

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
