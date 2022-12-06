package uc.doc.submitsolution;

import entities.*;
import entities.factories.*;

import java.time.LocalDateTime;

/**
 * SubmitSolutionDocInteractor implements the ability to submit a solution document into persistent data
 * @layer Use cases
 */
public class SubmitSolutionDocInteractor implements SubmitSDocInputBoundary {

    private final SubmitSDocOutputBoundary sDocOutputBoundary;

    private final SubmitSDocDsGateway sDocDsGateway;

    private final SubmitSDocFileAccessGateway sDocFileAccessGateway;

    private final SolutionDocFactory solutionDocFactory;

    private final StateTracker stateTracker;

    /**
     * Creates an interactor for the solution doc submission use case which contains the DsGateway, Output Boundary,
     * FileAccessGateway and StateTracker
     * @param sDocDsGateway Provides methods to store document into persistent memory
     * @param sDocOutputBoundary Provides methods to update views and give user feedback
     * @param sDocFileAccessGateway The file access gateway for the solution document submission use case
     * @param stateTracker Used for tracking entities in the program
     * @param solutionDocFactory The factory for creating solution documents
     */
    public SubmitSolutionDocInteractor(SubmitSDocDsGateway sDocDsGateway,
                                       SubmitSDocFileAccessGateway sDocFileAccessGateway,
                                       SubmitSDocOutputBoundary sDocOutputBoundary,
                                       StateTracker stateTracker,
                                       SolutionDocFactory solutionDocFactory) {
        this.sDocDsGateway = sDocDsGateway;
        this.sDocFileAccessGateway = sDocFileAccessGateway;
        this.sDocOutputBoundary = sDocOutputBoundary;
        this.stateTracker = stateTracker;
        this.solutionDocFactory = solutionDocFactory;
    }

    /**
     * Takes in the needed information for and creates a new SolutionDoc entity
     * @param model The solution document submission model containing all the relevant information for creating a new
     *              solution doc entity
     * @return If completed, the success view response model, containing information to be presented to the user
     */
    @Override
    public SubmitSDocResponseModel submitSolutionDoc(SubmitSDocRequestModel model) {
        Course course  = stateTracker.getCourseIfTracked(model.getCourseID());
        User user = stateTracker.getCurrentUser();
        TestDocument parentTest = course.getTest(model.getParentTestID());

        SubmitSDocDsRequestModel dsRequestModel = new SubmitSDocDsRequestModel(
                model.getName(),
                user.getId(),
                model.getRecordedScore(),
                model.getCourseID(),
                model.getParentTestID(),
                model.getFilePath(),
                "",
                0,
                model.getRecordedTime()
        );

        String solutionId = sDocDsGateway.saveSolutionDocument(dsRequestModel);
        String rootMessageId = sDocDsGateway.addRootMessage(
                solutionId,
                user.getId()
        );

        sDocDsGateway.updateRootMessageIdOfSolution(
                solutionId,
                rootMessageId);

        sDocFileAccessGateway.uploadSolutionDocument(dsRequestModel, solutionId);

        SolutionDocument document = solutionDocFactory.create(
                model.getName(),
                solutionId,
                course,
                user,
                model.getRecordedScore(),
                parentTest,
                model.getRecordedTime(),
                rootMessageId);

        parentTest.addUpdateSolution(document);

        SubmitSDocResponseModel responseModel = new SubmitSDocResponseModel(document.getId(), parentTest.getId(), LocalDateTime.now());

        return sDocOutputBoundary.prepareSuccessView(responseModel);
    }
}
