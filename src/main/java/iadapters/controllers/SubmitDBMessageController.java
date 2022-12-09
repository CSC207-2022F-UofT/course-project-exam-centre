package iadapters.controllers;

import usecases.submessage.SubDBMessInputBoundary;
import usecases.submessage.SubDBMessRequestModel;
import usecases.submessage.SubDBMessResponseModel;

/**
 *  SubmitDBMessageController provides an entry way into the SubmitDBMessage use case
 * @layer interface adapters
 */
public class SubmitDBMessageController {
    private final SubDBMessInputBoundary inputBoundary;

    /**
     * Constructs an instance of SubmitDBMessageController with an input boundary
     * @param inputBoundary provides methods to invoke use case
     */
    public SubmitDBMessageController(
            SubDBMessInputBoundary inputBoundary){
        this.inputBoundary = inputBoundary;
    }

    /**
     * Invokes the SubmitDBMessage use case
     * @param solutionId id of the solution document
     * @param userId the id of the user leaving the message
     * @param parentId id of the parent message
     * @param body body of the message
     * @return a response model corresponding to the request model
     */
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
