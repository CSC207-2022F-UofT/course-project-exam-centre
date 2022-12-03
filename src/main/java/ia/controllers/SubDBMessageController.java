package ia.controllers;

import uc.dboard.submessage.SubDBMessInputBoundary;
import uc.dboard.submessage.SubDBMessRequestModel;
import uc.dboard.submessage.SubDBMessResponseModel;

public class SubDBMessageController {
    private final SubDBMessInputBoundary inputBoundary;

    public SubDBMessageController(
            SubDBMessInputBoundary inputBoundary){
        this.inputBoundary = inputBoundary;
    }

    public SubDBMessResponseModel registerCourse(
            String solutionId,
            String userId,
            String parentId,
            String body) {

        SubDBMessRequestModel requestModel = new SubDBMessRequestModel(
                solutionId,
                userId,
                parentId,
                body);

        return inputBoundary.submitMessage(requestModel);
    }
}
