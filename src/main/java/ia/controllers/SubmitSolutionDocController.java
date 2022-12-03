package ia.controllers;

import uc.doc.submitsolution.SubSDocRequestModel;
import uc.doc.submitsolution.SubSDocResponseModel;
import uc.doc.submitsolution.SubmitSDocInputBoundary;

public class SubmitSolutionDocController{

    private final SubmitSDocInputBoundary inputBoundary;

    public SubmitSolutionDocController(SubmitSDocInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public SubSDocResponseModel SubmitSolutionDoc(String name,
                                                  Float recordedScore,
                                                  String courseID,
                                                  Float recordedTime,
                                                  String parentTestID,
                                                  String filePath) {

        SubSDocRequestModel responseModel = new SubSDocRequestModel(
                name,
                filePath,
                recordedScore,
                courseID,
                recordedTime,
                parentTestID
                );

        return inputBoundary.submitSolutionDoc(responseModel);
    }
}
